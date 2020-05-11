package com.ShapeShifter420.evmod.gui;

import com.ShapeShifter420.evmod.EVmod;
import com.ShapeShifter420.evmod.block.BlockList;
import com.ShapeShifter420.evmod.inventory.CraftingSlot;
import com.ShapeShifter420.evmod.inventory.ICraftCustom;
import com.ShapeShifter420.evmod.inventory.InventoryCraftingPersistent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class BaseContainer extends Container implements ICraftCustom {

        public final TileEntityBase_C tileEntity;
        private final IWorldPosCallable canInteractWithCallable;
        public InventoryCraftingPersistent craftMatrix;
        public IInventory craftResult;

        private ItemStack output;
        public BaseContainer(final int windowId, final PlayerInventory playerInventory,
                             final TileEntityBase_C tileEntity) {
            super(ModContainerTypes.EXAMPLE_CHEST.get(), windowId);
            this.tileEntity = tileEntity;
            this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
            this.output = ItemStack.EMPTY;
            this.craftMatrix = new InventoryCraftingPersistent(this, tileEntity, 1, 1);
            this.craftResult = new CraftResultInventory();

            // Main Inventory
            int startX = 8;
            int startY = 18;
            int slotSizePlus2 = 18;
            this.addSlot(new Slot(this.craftMatrix, 0, 48, 35));
            this.addSlot(new CraftingSlot(this, playerInventory.player, craftMatrix, craftResult, 1, 106, 35));

            // Main Player Inventory
            int startPlayerInvY = startY * 5 + 12;
            for (int row = 0; row < 3; ++row) {
                for (int column = 0; column < 9; ++column) {
                    this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
                            startPlayerInvY + (row * slotSizePlus2)));
                }
            }

            // Hotbar
            int hotbarY = startPlayerInvY + (startPlayerInvY / 2) + 7;
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hotbarY));
            }
        }

        private static TileEntityBase_C getTileEntity(final PlayerInventory playerInventory,
                                                      final PacketBuffer data) {
            Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
            Objects.requireNonNull(data, "data cannot be null");
            final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
            if (tileAtPos instanceof TileEntityBase_C) {
                return (TileEntityBase_C) tileAtPos;
            }
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }

        public BaseContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
            this(windowId, playerInventory, getTileEntity(playerInventory, data));
        }

        @Override
        public boolean canInteractWith(PlayerEntity playerIn) {
            return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockList.BASE_COMPUTER.get());
        }

        @Override
        public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
            ItemStack itemstack = ItemStack.EMPTY;
            Slot slot = this.inventorySlots.get(index);
            if (slot != null && slot.getHasStack()) {
                ItemStack itemstack1 = slot.getStack();
                itemstack = itemstack1.copy();
                if (index < 36) {
                    if (!this.mergeItemStack(itemstack1, 36, this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.mergeItemStack(itemstack1, 0, 36, false)) {
                    return ItemStack.EMPTY;
                }

                if (itemstack1.isEmpty()) {
                    slot.putStack(ItemStack.EMPTY);
                } else {
                    slot.onSlotChanged();
                }
            }
            return itemstack;
        }
    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        updateResult();
    }
    @Override
    public void onCrafting(PlayerEntity player, ItemStack output, IInventory craftMatrix) {
        ItemStack itemstack1 = craftMatrix.getStackInSlot(0);

        // Assumption: Only 1 input, will always be decreased by only 1
        if(!itemstack1.isEmpty()) {
            craftMatrix.decrStackSize(0, 1);
        }

        updateResult();
    }
    public void updateResult() {
        // no pattern :(
        if(craftMatrix.getStackInSlot(0).isEmpty() || output.isEmpty()) {
            craftResult.setInventorySlotContents(0, ItemStack.EMPTY);
        }
        else {
            // set pattern from selection (or null if no selection)
            craftResult.setInventorySlotContents(0, output.copy());
        }
    }


}