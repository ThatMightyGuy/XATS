package io.github.thatmightyguy.xasm.token;

public class FloatToken implements XasmToken {
    private Float value;

    public FloatToken(String value) {
        this.value = Float.parseFloat(value);
    }

    public Float get() {
        return value;
    }
}
