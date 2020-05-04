package com.ShapeShifter420.evmod.block;

import com.ShapeShifter420.evmod.EVmod;
import com.ShapeShifter420.evmod.block.BlockList;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class SimpleBlockItemInit extends BlockItem {

    public SimpleBlockItemInit(Block blockIn, String name) {

        super(blockIn, new Item.Properties()
                .group(EVmod.EVItemGroup.instance));
        this.setRegistryName(name);
        //BlockList.BlockItems.add(this);

    }
}

