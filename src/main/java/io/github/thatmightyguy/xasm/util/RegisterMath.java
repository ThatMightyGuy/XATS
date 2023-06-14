package io.github.thatmightyguy.xasm.util;

import io.github.thatmightyguy.xasm.arch.XaRegister;

public class RegisterMath {
    private static void error1(String op, XaRegister a) {
        throw new IllegalArgumentException(String.format(
            "Unable to %s a %s", op,
            a.getType().getSimpleName()
        ));
    }

    private static void error2(String op, XaRegister a, XaRegister b) {
        throw new IllegalArgumentException(String.format(
            "Unable to %s %s and %s", op,
            a.getType().getSimpleName(), b.getType().getSimpleName()
        ));
    }

    public static XaRegister add(XaRegister a, XaRegister b) {
        Object va = a.get();
        Object vb = b.get();
         
        if(a.getType() == Float.class && b.getType() == Float.class)
            return new XaRegister((Float)va + (Float)vb, Float.class);
        return new XaRegister(va.toString() + vb.toString(), String.class);
    }

    public static XaRegister sub(XaRegister a, XaRegister b) {
        Object va = a.get();
        Object vb = b.get();

        if(a.getType() == Float.class && b.getType() == Float.class)
            return new XaRegister((Float)va - (Float)vb, Float.class);
        error2("subtract", a, b);
        return null;
    }

    public static XaRegister div(XaRegister a, XaRegister b) {
        Object va = a.get();
        Object vb = b.get();

        if(a.getType() == Float.class && b.getType() == Float.class)
            return new XaRegister((Float)va / (Float)vb, Float.class);
        error2("divide", a, b);
        return null;
    }

    public static XaRegister mod(XaRegister a, XaRegister b) {
        Object va = a.get();
        Object vb = b.get();

        if(a.getType() == Float.class && b.getType() == Float.class)
            return new XaRegister((Float)va % (Float)vb, Float.class);
        error2("modulo", a, b);
        return null;
    }

    public static XaRegister mul(XaRegister a, XaRegister b) {
        Object va = a.get();
        Object vb = b.get();

        if(a.getType() == Float.class && b.getType() == Float.class)
            return new XaRegister((Float)va * (Float)vb, Float.class);
        if(a.getType() == String.class && b.getType() == Float.class)
            return new XaRegister(new String(new char[(int)Math.floor((float)vb)]).replace("\0", va.toString()), String.class);
        error2("multiply", a, b);
        return null;
    }

    public static XaRegister inc(XaRegister a) {
        if(a.getType() == Float.class)
            return new XaRegister((Float)a.get() + 1f, Float.class);
        error1("increment", a);
        return null;
    }

    public static XaRegister dec(XaRegister a) {
        if(a.getType() == Float.class)
            return new XaRegister((Float)a.get() - 1f, Float.class);
        error1("decrement", a);
        return null;
    }

    public static XaRegister ceil(XaRegister a) {
        if(a.getType() == Float.class)
            return new XaRegister((float)Math.ceil((Float)a.get()), Float.class);
        error1("ceil", a);
        return null;
    }

    public static XaRegister floor(XaRegister a) {
        if(a.getType() == Float.class)
            return new XaRegister((float)Math.ceil((Float)a.get()), Float.class);
        error1("floor", a);
        return null;
    }

    public static XaRegister length(XaRegister a) {
        if(a.getType() == String.class)
            return new XaRegister((a.get().toString()).length(), Float.class);
        error1("size", a);
        return null;
    }

    public static XaRegister cmp(XaRegister a, XaRegister b) {
        if(a.getType() == Float.class && b.getType() == Float.class)
            return new XaRegister(Math.signum((Float)a.get() - (Float)b.get()), Float.class);
        if(a.getType() == String.class && b.getType() == Float.class)
            return new XaRegister(a.get() == b.get() ? 1f : 0f, Float.class);
        error2("compare", a, b);
        return null;
    }

    public static XaRegister copy(XaRegister to, XaRegister from) {
        to.update(from.get(), from.getType());
        return to;
    }
}
