package com.ShapeShifter420.evmod.tools;

import com.ShapeShifter420.evmod.block.BlockList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;

import java.util.Set;

public class SolderinIronTool extends ToolItem {
    private static final Set<Block> EFFECTIVE_ON = ImmutableSet.of(BlockList.chip_block.getBlock(),BlockList.power_block.getBlock());
    public SolderinIronTool(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super((float) attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder.addToolType(net.minecraftforge.common.ToolType.PICKAXE, tier.getHarvestLevel()));
    }
}
