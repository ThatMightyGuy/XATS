package io.github.thatmightyguy.xasm.arch;

import io.github.thatmightyguy.xasm.Xasm;
import io.github.thatmightyguy.xasm.token.*;
import io.github.thatmightyguy.xasm.util.RegisterMath;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Runner {
    private static List<XaRegister> flatten(Map<String, XaRegister> registers, List<XasmToken> args) {
        List<XaRegister> result = new ArrayList<>();
        for(XasmToken tok : args) {
            if(tok.getClass() == StringToken.class)
                result.add(new XaRegister(tok.get(), String.class));
            else if(tok.getClass() == FloatToken.class)
                result.add(new XaRegister(tok.get(), Float.class));
            else if(tok.getClass() == RegisterToken.class)
                result.add(registers.get(tok.get()));
        }
        return result;
    }

    @SuppressWarnings("java:S3776") // code complexity, can't think of a way to reduce
    public static short runOnce(List<List<XasmToken>> code, short instructionPointer, Map<String, XaRegister> registers) {
        short offset = 1;
        List<XasmToken> instruction = code.get(instructionPointer);
        XasmTokens opcode = (XasmTokens)instruction.get(0).get();
        List<XaRegister> args = flatten(registers, instruction);

        switch(opcode) {
            case NOP:
                break;
            case KILL:
                offset = -Definitions.MAX_PROGRAM;
                break;
            case ADD:
                registers.get(Definitions.REGISTERS[0]).update(RegisterMath.add(args.get(0), args.get(1)));
                break;
            case SUB:
                registers.get(Definitions.REGISTERS[0]).update(RegisterMath.sub(args.get(0), args.get(1)));
                break;
            case MUL:
                registers.get(Definitions.REGISTERS[0]).update(RegisterMath.mul(args.get(0), args.get(1)));
                break;
            case DIV:
                registers.get(Definitions.REGISTERS[0]).update(RegisterMath.div(args.get(0), args.get(1)));
                registers.get(Definitions.REGISTERS[1]).update(RegisterMath.mod(args.get(0), args.get(1)));
                break;
            case INC:
                args.get(0).update(RegisterMath.inc(args.get(0)));
                break;
            case DEC:
                args.get(0).update(RegisterMath.dec(args.get(0)));
                break;
            case CEL:
                registers.get(Definitions.REGISTERS[0]).update(RegisterMath.ceil(args.get(0)));
                break;
            case FLR:
                registers.get(Definitions.REGISTERS[0]).update(RegisterMath.floor(args.get(0)));
                break;
            case LEN:
                registers.get(Definitions.REGISTERS[0]).update(RegisterMath.length(args.get(0)));
                break;
            case CMP:
                registers.get(Definitions.REGISTERS[0]).update(RegisterMath.cmp(args.get(0), args.get(1)));
                break;
            case JL:
                if(registers.get(Definitions.REGISTERS[0]).getType() == Float.class &&
                    (Float)registers.get(Definitions.REGISTERS[0]).get() < 0)
                    offset = (short)(Xasm.getLabel(code, args.get(0).toString()) - instructionPointer);
                break;
            case JLE:
                if(registers.get(Definitions.REGISTERS[0]).getType() == Float.class &&
                    (Float)registers.get(Definitions.REGISTERS[0]).get() <= 0)
                    offset = (short)(Xasm.getLabel(code, args.get(0).toString()) - instructionPointer);
                break;
            case JZ:
                if(registers.get(Definitions.REGISTERS[0]).getType() == Float.class &&
                    (Float)registers.get(Definitions.REGISTERS[0]).get() == 0)
                    offset = (short)(Xasm.getLabel(code, args.get(0).toString()) - instructionPointer);
                break;
            case JNZ:
                if(registers.get(Definitions.REGISTERS[0]).getType() == Float.class &&
                    (Float)registers.get(Definitions.REGISTERS[0]).get() != 0)
                    offset = (short)(Xasm.getLabel(code, args.get(0).toString()) - instructionPointer);
                break;
            case JGE:
                if(registers.get(Definitions.REGISTERS[0]).getType() == Float.class &&
                    (Float)registers.get(Definitions.REGISTERS[0]).get() >= 0)
                    offset = (short)(Xasm.getLabel(code, args.get(0).toString()) - instructionPointer);
                break;
            case JG:
                if(registers.get(Definitions.REGISTERS[0]).getType() == Float.class &&
                    (Float)registers.get(Definitions.REGISTERS[0]).get() > 0)
                    offset = (short)(Xasm.getLabel(code, args.get(0).toString()) - instructionPointer);
                break;
            case JMP:
                offset = (short)(Xasm.getLabel(code, args.get(0).toString()) - instructionPointer);
                break;
            case MOV:
                args.get(0).update(args.get(1));
                break;
            default:
                throw new IllegalArgumentException("Not implemented: " + opcode.name());
        }
        return offset;
    }

    private Runner() {}
}
