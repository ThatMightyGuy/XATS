package io.github.thatmightyguy.xats;

import io.github.thatmightyguy.xasm.arch.Process;
import io.github.thatmightyguy.xats.block.ModBlocks;
import io.github.thatmightyguy.xats.item.ModItems;
import io.github.thatmightyguy.xats.util.CommonProxy;
import io.github.thatmightyguy.xats.util.XatsCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(modid = Entrypoint.MODID, name = Entrypoint.NAME, version = Entrypoint.VERSION)
public class Entrypoint
{
    public static final String MODID = "xats";
    public static final String NAME = "XATS";
    public static final String VERSION = "1.12.2-1.0.0.0";

    public static XatsCreativeTab modTab;

    public static Logger logger;

    @Mod.Instance(MODID)
    public static Entrypoint instance;

    @SidedProxy(serverSide = "io.github.thatmightyguy.xats.util.CommonProxy", clientSide = "io.github.thatmightyguy.xats.util.ClientProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        modTab = new XatsCreativeTab();
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> e) {
            logger.info("Registering blocks");
            ModBlocks.registerBlocks(e);
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> e) {
            logger.info("Registering items");
            ModItems.registerItems(e);
        }

        private RegistrationHandler() {}
    }
}