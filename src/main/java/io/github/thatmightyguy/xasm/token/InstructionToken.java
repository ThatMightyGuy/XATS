package io.github.thatmightyguy.xasm.token;

public class InstructionToken implements XasmToken {
    private XasmTokens value;

    public InstructionToken(String value) {
        this.value = XasmTokens.valueOf(value);
    }

    public XasmTokens get() {
        return value;
    }
}
