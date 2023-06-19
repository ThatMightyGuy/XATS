package io.github.thatmightyguy.xats.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItem extends Item {
    public ModItem(String name) {
        super();
        setUnlocalizedName(name);
		setRegistryName(name);
    }

    @Override
	public ModItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
