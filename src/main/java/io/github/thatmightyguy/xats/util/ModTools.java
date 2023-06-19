package io.github.thatmightyguy.xats.util;

import io.github.thatmightyguy.xats.Entrypoint;

public class ModTools {
    public static String prependId(String s) {
        return Entrypoint.MODID + ":" + s;
    }

    private ModTools() {}
}
