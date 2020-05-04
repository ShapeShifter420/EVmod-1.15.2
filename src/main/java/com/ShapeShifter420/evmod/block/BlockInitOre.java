package com.ShapeShifter420.evmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.ToolType;

public class BlockInitOre extends Block {

    public BlockInitOre(String name, Float resist) {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(resist)
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.STONE));
        this.setRegistryName(name);
        BlockList.Blocks.add(this);
        BlockItem ore_copper = new SimpleBlockItemInit(this, name);
        BlockList.BlockItems.add(ore_copper);
    }
}
