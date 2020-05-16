package com.ShapeShifter420.evmod.gui;

import com.ShapeShifter420.evmod.recieps.ComputerReciepts;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class ComputerContainer extends Container
{
    public final TileEntityComputer tileentity;
    private int cookTime, energy;

    public ComputerContainer(final int windowId,final PlayerInventory player,final TileEntityComputer tileentity)
    {
        super(ModContainerTypes.EXAMPLE_CHEST.get(), windowId);
        this.tileentity = tileentity;
        IItemHandler handler = (IItemHandler) tileentity.handler;
        int startX = 8;
        int startY = 18;
        int slotSizePlus2 = 18;
        this.addSlot(new SlotItemHandler(handler, 0, 44, 21));
        this.addSlot(new SlotItemHandler(handler, 1, 44, 50));
        this.addSlot(new SlotItemHandler(handler, 2, 97, 36));

        // Main Player Inventory
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlot(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlot(new Slot(player, x, 8 + x * 18, 142));
        }
    }
    public ComputerContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }
    private static TileEntityComputer getTileEntity(final PlayerInventory playerInventory,
                                                  final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof TileEntityComputer) {
            return (TileEntityComputer) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }
    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return this.tileentity.isUsableByPlayer(playerIn);
    }

    @Override
    public void updateProgressBar(int id, int data)
    {
        this.tileentity.setField(id, data);
    }

    @Override
    public void detectAndSendChanges()
    {
        List<IContainerListener> listeners = Lists.newArrayList();
        super.detectAndSendChanges();
        try {
            Field fieldA = super.getClass().getDeclaredField("listeners");
            fieldA.setAccessible(true);
            listeners = (List<IContainerListener>)fieldA.get(super.getClass());
        } catch (NoSuchFieldException | IllegalAccessException e) {
           e.printStackTrace();
        }
        for(int i = 0; i < listeners.size(); ++i)
        {
            System.out.println(666);
            IContainerListener listener = (IContainerListener)listeners.get(i);

            if(this.cookTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
            if(this.energy != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
        }

        this.cookTime = this.tileentity.getField(0);
        this.energy = this.tileentity.getField(1);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index == 2)
            {
                if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
                slot.onSlotChange(stack1, stack);
            }
            else if(index != 2 && index != 1 && index != 0)
            {
                Slot slot1 = (Slot)this.inventorySlots.get(index + 1);

                if(!ComputerReciepts.getInstance().getSinteringResult(stack1, slot1.getStack()).isEmpty())
                {
                    if(!this.mergeItemStack(stack1, 0, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    else if(index >= 4 && index < 31)
                    {
                        if(!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
                    }
                    else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else if(!this.mergeItemStack(stack1, 4, 40, false))
            {
                return ItemStack.EMPTY;
            }
            if(stack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();

            }
            if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
            slot.onTake(playerIn, stack1);
        }
        return stack;
    }
}