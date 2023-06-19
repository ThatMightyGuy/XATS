package io.github.thatmightyguy.xats.util;

import io.github.thatmightyguy.xats.block.ModBlocks;
import io.github.thatmightyguy.xats.Entrypoint;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class XatsCreativeTab extends CreativeTabs {
    public XatsCreativeTab() {
        super(Entrypoint.MODID);
    }

    @Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.MOD_BLOCKS.get("mcu"));
	}
}
