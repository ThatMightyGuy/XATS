package io.github.thatmightyguy.xats.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class MicrocontrollerBlock extends Block {

    public MicrocontrollerBlock() {
        super(Properties.of(Material.STONE));
    }

    public String exposeTest(String xdd) {
        return xdd.toUpperCase();
    }
}
