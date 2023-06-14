package io.github.thatmightyguy.xasm.arch;

import io.github.thatmightyguy.xasm.Xasm;
import io.github.thatmightyguy.xasm.token.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Process {
    private final Map<String, XaRegister> registers = new HashMap<>();
    private short instructionPointer = 0;
    private List<List<XasmToken>> code;

    public Process(String code) {
        for(String reg : Definitions.REGISTERS)
            registers.put(reg, new XaRegister(0f, Float.class));
        compile(code);
    }

    public XaRegister getRegister(String register) {
        return registers.get(register);
    }

    public void setRegisterString(String register, String value) {
        registers.get(register).update(value, String.class);
    }
    
    public void setRegisterFloat(String register, Float value) {
        registers.get(register).update(value, Float.class);
    }

    public void compile(String code) {
        List<List<XasmToken>> tokens = Xasm.tokenize(code);
        short tokenCount = 0;
        for(List<XasmToken> line : tokens)
            tokenCount += (short)line.size();
        if(tokenCount > Definitions.MAX_PROGRAM)
            throw new IllegalArgumentException(
                String.format("Code is longer than %s tokens", Definitions.MAX_PROGRAM)
            );
        this.code = tokens;
    }

    @SuppressWarnings("java:S2864")
    public void reset() {
        instructionPointer = 0;
        for(String k : registers.keySet())
            registers.get(k).update(0f, Float.class);
        code.clear();
    }
    
    public void tick() {
        instructionPointer += Runner.run(code, instructionPointer, registers);
    }

    public List<List<XasmToken>> getTokens() {
        return code;
    }
}
