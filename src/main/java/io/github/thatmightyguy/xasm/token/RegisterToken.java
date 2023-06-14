package io.github.thatmightyguy.xasm.token;

public class RegisterToken implements XasmToken {
    private String value;

    public RegisterToken(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
