package com.ShapeShifter420.evmod.tools;


import com.ShapeShifter420.evmod.EVmod;
import com.ShapeShifter420.evmod.items.ItemList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WiresStick extends Item
{
    public WiresStick(String name)
    {
        super(new Item.Properties()
                .group(EVmod.EVItemGroup.instance));
        this.setRegistryName(name);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack item = playerIn.getHeldItem(handIn);
        Vec3d aim = playerIn.getLookVec();
        playerIn.getCooldownTracker().setCooldown(this, 1);
        createEntity(worldIn,new LightningBoltEntity(worldIn, playerIn.prevPosX+aim.x*1.5D,playerIn.prevPosY+aim.y*1.5D,playerIn.prevPosZ+aim.z*1.5D, false),item);
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);
    }
}
