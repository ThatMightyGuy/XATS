package io.github.thatmightyguy.xats.block;

import io.github.thatmightyguy.xats.Entrypoint;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlock extends Block {
	public ModBlock(Material material, String name) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(Entrypoint.MODID, name);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@Override
	public ModBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
