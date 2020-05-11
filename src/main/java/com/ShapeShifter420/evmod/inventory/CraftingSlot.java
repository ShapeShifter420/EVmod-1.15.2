package com.ShapeShifter420.evmod.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CraftingSlot extends CraftingResultSlot {

    private final CraftingInventory craftMatrix;
    private final ICraftCustom callback;

    /**
     * @param callback          Container that gets the crafting call on crafting
     * @param player            Player that does the crafting
     * @param craftingInventory Inventory where the ingredients are taken from
     * @param craftResult       Inventory where the result is put
     */
    public CraftingSlot(ICraftCustom callback, PlayerEntity player, CraftingInventory craftingInventory, IInventory craftResult, int slotIndex, int xPosition, int yPosition) {
        super(player, craftingInventory, craftResult, slotIndex, xPosition, yPosition);

        this.craftMatrix = craftingInventory;
        this.callback = callback;
    }

    //@Override
    //@Nonnull
    //public ItemStack onTake(PlayerEntity playerIn, @Nonnull ItemStack stack) {
        //net.minecraftforge.fml.loading.FMLCommonLaunchHandlerHandler.instance().firePlayerCraftingEvent(playerIn, stack, craftMatrix);
        //this.onCrafting(stack);

        //callback.onCrafting(playerIn, stack, craftMatrix);

        //return stack;
    //}
}
