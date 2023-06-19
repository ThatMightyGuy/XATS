package io.github.thatmightyguy.xats.item;

import java.util.Map;

import io.github.thatmightyguy.xats.Entrypoint;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public class ModItems {
    public static final Map<String, ModItem> MOD_ITEMS = new HashMap<>();

    public static void registerItems(RegistryEvent.Register<Item> e) {


        IForgeRegistry<Item> registry = e.getRegistry();
        for(Map.Entry<String, ModItem> kvp : MOD_ITEMS.entrySet())
            registry.register(kvp.getValue().setCreativeTab(Entrypoint.modTab));
    }

    private ModItems() {}
}
