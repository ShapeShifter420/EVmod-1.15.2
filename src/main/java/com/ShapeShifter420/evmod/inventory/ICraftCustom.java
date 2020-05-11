package com.ShapeShifter420.evmod.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface ICraftCustom {

    void onCrafting(PlayerEntity player, ItemStack output, IInventory craftMatrix);
}
