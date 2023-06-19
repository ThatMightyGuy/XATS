package io.github.thatmightyguy.xats.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class MicrocontrollerBlock extends ModBlock {

    public MicrocontrollerBlock() {
        super(Material.ROCK, "mcu");
    }

    @Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

    public String exposeTest(String xdd) {
        return xdd.toUpperCase();
    }
}
