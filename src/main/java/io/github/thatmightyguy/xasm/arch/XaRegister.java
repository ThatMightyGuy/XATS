package io.github.thatmightyguy.xasm.arch;

public class XaRegister {
    private Object value;
    private Class<?> type;

    public XaRegister(Object value, Class<?> type) {
        update(value, type);
    }

    public void update(Object value, Class<?> type) { // Updates both calue and type
        this.value = value;
        this.type = type;
    }

    public void update(XaRegister copy) {
        update(copy.value, copy.type);
    }

    public void update(Object value) { // Casts value to current type
        this.value = type.getClass().cast(value);
    }

    public Object get() {
        return value;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("XaRegister(%s, %s)", type.cast(value), type.getName());
    }
}
