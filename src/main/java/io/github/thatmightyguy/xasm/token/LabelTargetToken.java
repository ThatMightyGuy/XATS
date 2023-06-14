package io.github.thatmightyguy.xasm.token;

public class LabelTargetToken implements XasmToken {
    private String value;

    public LabelTargetToken(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
