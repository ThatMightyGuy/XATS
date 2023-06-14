package io.github.thatmightyguy.xasm.token;

public class LabelToken implements XasmToken {
    private String value;

    public LabelToken(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
