package com.ShapeShifter420.evmod.block;

import com.ShapeShifter420.evmod.objects.ComputerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.ToolType;

public class BlockInit extends Block {

    public BlockInit(String name, Float resist) {
        super(Block.Properties.from(new ComputerBlock(Properties.create(Material.IRON)))
                .hardnessAndResistance(resist)
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.METAL));
        this.setRegistryName(name);
        BlockList.Blocks.add(this);
        BlockItem base_computer = new SimpleBlockItemInit(this, name);
        BlockList.BlockItems.add(base_computer);
    }
}

