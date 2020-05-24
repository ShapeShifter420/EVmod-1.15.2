package com.ShapeShifter420.evmod.tools;

import com.ShapeShifter420.evmod.block.BlockList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Set;

public class SolderinIronTool extends ToolItem {
    private static final Set<Block> EFFECTIVE_ON = ImmutableSet.of(BlockList.mk3_2_2.getBlock(),BlockList.mk3_2_1.getBlock(),BlockList.mk3_2_0.getBlock(),BlockList.mk3_1_2.getBlock(),BlockList.mk3_1_0.getBlock(),BlockList.mk3_0_2.getBlock(),BlockList.mk3_1_1.getBlock(),BlockList.mk3_0_1.getBlock(),BlockList.mk2_down.getBlock(),BlockList.mk2_recorder.getBlock(),BlockList.mk2_up.getBlock(),BlockList.NANOCHEST.get(),BlockList.BASE_COMPUTER.get());
    public SolderinIronTool(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super((float) attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder.addToolType(net.minecraftforge.common.ToolType.PICKAXE, tier.getHarvestLevel()));
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        Vec3d aim = playerIn.getLookVec();
        FireballEntity fire = new FireballEntity(worldIn, playerIn, 1, 1, 1);
        fire.setPosition(playerIn.prevPosX + aim.x * 1.5D, playerIn.prevPosY + aim.y * 1.5D, playerIn.prevPosZ + aim.z * 1.5D);
        fire.accelerationX = aim.x * 0.1;
        fire.accelerationY = aim.y * 0.1;
        fire.accelerationZ = aim.z * 0.1;
        System.out.println(444);
        createEntity(worldIn, fire, item);
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);
    }
}
