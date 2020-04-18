package com.ShapeShifter420.evmod.items;

import com.ShapeShifter420.evmod.EVmod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;


@Mod.EventBusSubscriber(modid = EVmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EVmod.MOD_ID)
public class ItemInit {
    public static final Item cpu = null;
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry()
                .register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("controller"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("copper"));
        event.getRegistry()
            .register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("plastic"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("punch card"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("bitcoin"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("wolfram"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("chip"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("battery"));
    }
}

