package com.ShapeShifter420.evmod.items;

import com.ShapeShifter420.evmod.EVmod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

public class SimpleItemInit extends Item {

    public SimpleItemInit(String name) {
        super(new Item.Properties()
                .group(EVmod.EVItemGroup.instance));
        this.setRegistryName(name);
        ItemList.Items.add(this);
    }
}
