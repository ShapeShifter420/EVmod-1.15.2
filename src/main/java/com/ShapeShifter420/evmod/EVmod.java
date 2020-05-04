package com.ShapeShifter420.evmod;

import com.ShapeShifter420.evmod.block.BlockInitComputer;
import com.ShapeShifter420.evmod.block.BlockList;
import com.ShapeShifter420.evmod.block.SimpleBlockItemInit;
import com.ShapeShifter420.evmod.world.OreGeneration;
import com.ShapeShifter420.evmod.items.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("evmod")
public class EVmod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "evmod";
    public static EVmod instance;

    public EVmod() {
        // Register the setup method for modloading
        final IEventBus modEventBus =FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::loadCompleteEvent);
        modEventBus.addListener(this::setup);
        BlockList.BLOCKS.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);

    };
    @SubscribeEvent
    public void loadCompleteEvent(FMLLoadCompleteEvent event) {
        OreGeneration.generateOre();
    }
    private void setup(FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    public static class EVItemGroup extends ItemGroup {
        public static final ItemGroup instance = new EVItemGroup(ItemGroup.GROUPS.length, "evmodtab");

        private EVItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemList.chip);
        }
    }
}
