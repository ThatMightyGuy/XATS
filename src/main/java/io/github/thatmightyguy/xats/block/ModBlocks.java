package io.github.thatmightyguy.xats.block;

import java.util.Map;

import io.github.thatmightyguy.xats.Entrypoint;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public class ModBlocks {
    public static final Map<String, ModBlock> MOD_BLOCKS = new HashMap<>();

    public static MicrocontrollerBlock mcu;

    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        MOD_BLOCKS.put("mcu", new MicrocontrollerBlock());

        mcu = new MicrocontrollerBlock();
        mcu.setCreativeTab(CreativeTabs.REDSTONE);

        IForgeRegistry<Block> registry = e.getRegistry();
        registry.register(mcu);
        

        // for(Map.Entry<String, ModBlock> kvp : MOD_BLOCKS.entrySet()) {
        //     registry.register(kvp.getValue().setCreativeTab(Entrypoint.modTab));
        // }
    }

    private ModBlocks() {}
}
