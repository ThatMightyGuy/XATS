package io.github.thatmightyguy.xasm;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.CharMatcher;

import java.util.ArrayList;

import io.github.thatmightyguy.xasm.arch.Definitions;
import io.github.thatmightyguy.xasm.token.*;

public class Xasm {
    private Xasm() {}
    
    private static List<List<XasmToken>> categorize(List<List<String>> tokens) {
        List<List<XasmToken>> tokenList = new ArrayList<>();

        for(int i = 0; i < tokens.size(); i++) {
            List<XasmToken> newTokens = new ArrayList<>();
            List<String> line = tokens.get(i);
            
            String opcode = line.get(0);
            if(opcode.startsWith(":"))
                newTokens.add(new LabelToken(opcode.substring(1)));
            else if(EnumUtils.isValidEnum(XasmTokens.class, opcode))
                newTokens.add(new InstructionToken(opcode));
            else
                throw new IllegalArgumentException("Invalid instruction on line " + i + " token 0");

            for(int j = 1; j < line.size(); j++) {
                String tok = line.get(j);
                XasmToken token;
                if(ArrayUtils.contains(Definitions.REGISTERS, tok))
                    token = new RegisterToken(tok);
                else if(tok.startsWith("\"") && tok.endsWith("\""))
                    token = new StringToken(tok.substring(1, tok.length() - 1));
                else if(tok.startsWith("@"))
                    token = new LabelTargetToken(tok.substring(1));
                else if(NumberUtils.isCreatable(tok))
                    token = new FloatToken(tok);
                else
                    throw new IllegalArgumentException("Invalid instruction on line " + i + " token " + j + "(" + tok + ")");
                newTokens.add(token);
            }

            tokenList.add(newTokens);
        }
        return tokenList;
    }

    @SuppressWarnings("java:S3776") // code complexity, can't think of a way to reduce
    public static List<List<XasmToken>> tokenize(String code) {
        String[] lines = code.split("\\R");
        List<List<String>> tokens = new ArrayList<>();

        for (String l : lines) {
            List<String> lineTokens = new ArrayList<>();
            String line = CharMatcher.whitespace().trimFrom(l);
            if(line.length() == 0) continue;

            // i wish i understood regex enough

            StringBuilder word = new StringBuilder();
            Boolean inString = false;
            Boolean brk = false;
            Boolean inLabel = false;
            for(int i = 0; i < line.length(); i++) {
                if(Boolean.TRUE.equals(brk)) break;
                char c = line.charAt(i);
                switch(c) {
                    case '"':
                        word.append(c);
                        if(line.charAt(i - 1) != '\\')
                            inString = !inString;
                        break;
                    case ';':
                        if(Boolean.TRUE.equals(inString) && line.charAt(i - 1) == '\\')
                            word.append(c);
                        else
                            brk = true;
                        break;
                    case ':':
                        if(i == 0)
                            inLabel = true;
                        word.append(c);
                        break;
                    case ' ':
                        // If we're in a string AND NOT in a label, append the char
                        if(Boolean.TRUE.equals(inString) && Boolean.FALSE.equals(inLabel)) {
                            word.append(c);
                        } else {
                            lineTokens.add(word.toString());
                            word.setLength(0);
                        }
                        break;
                    default:
                        word.append(c);
                        break;
                }
            }
            lineTokens.add(word.toString());
            tokens.add(lineTokens);
        }

        return categorize(tokens);
    }
    
    public static short getLabel(List<List<XasmToken>> code, String name) {
        for(short i = 0; i < code.size(); i++)
            if(code.get(i).get(0).getClass() == LabelToken.class) {
                if(code.get(i).get(0).get() == name)
                    return i;
            }
        return -Definitions.MAX_PROGRAM;
    }
}
