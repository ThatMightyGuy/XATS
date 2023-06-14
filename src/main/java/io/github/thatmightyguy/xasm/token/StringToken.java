package io.github.thatmightyguy.xasm.token;

public class StringToken implements XasmToken {
    private String value;

    public StringToken(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
