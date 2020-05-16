package com.ShapeShifter420.evmod.gui;

import com.ShapeShifter420.evmod.gui.energy.EnergyStorage_C;
import com.ShapeShifter420.evmod.objects.ComputerBlock;
import com.ShapeShifter420.evmod.recieps.ComputerReciepts;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityComputer extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
    int tick;
    private EnergyStorage_C storage = new EnergyStorage_C(75000, 20, 0, 0);
    public ItemStackHandler handler = new ItemStackHandler(3);
    private String customName;
    public int cookTime, energy = storage.getEnergyStored();
    private ItemStack smelting = ItemStack.EMPTY;

    public TileEntityComputer(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public TileEntityComputer() {
        this(EntityType.EXAMPLE_CHEST.get());
    }

    public boolean hasCapability(Capability<?> capability, Direction facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        if(capability == CapabilityEnergy.ENERGY) return true;
        return super.getCapability(capability, facing) != null;
    }
    //@Override
    //public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing)
    //{
        //if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return this.handler;
        //if(capability == CapabilityEnergy.ENERGY) return this.storage;
        //return super.getCapability(capability, facing);
    //}

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);
        compound.put("Inventory", this.handler.serializeNBT());
        compound.putInt("CookTime", cookTime);
        compound.putInt("GuiEnergy", energy);
        this.storage.writeToNBT(compound);
        compound.putString("Name", getDefaultName().toString());
        return compound;
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        this.handler.deserializeNBT(compound.getCompound("Inventory"));
        this.storage.readFromNBT(compound);
        this.cookTime = compound.getInt("CookTime");
        this.energy = compound.getInt("GuiEnergy");
        if(compound.contains("Name")) this.customName = compound.getString("Name");
    }

    @Override
    public void tick()
    {
        tick++;
        if(tick > 20) tick = 0;

        if(tick == 0)
        {
            System.out.println(Integer.toString(energy));
        }

        if (energy<78000) energy+= 100;

        ItemStack[] inputs = new ItemStack[] {handler.getStackInSlot(0), handler.getStackInSlot(1)};

        if(energy >= 10000)
        {
            if(cookTime > 0)
            {
                energy -= 20;
                cookTime++;
                if(cookTime == 100)
                {
                    if(handler.getStackInSlot(2).getCount() > 0)
                    {
                        handler.getStackInSlot(2).grow(1);
                    }
                    else
                    {
                        handler.insertItem(2, smelting, false);
                    }
                    smelting = ItemStack.EMPTY;
                    cookTime = 0;
                    return;
                }
            }
            else
            {
                if(!inputs[0].isEmpty() && !inputs[1].isEmpty())
                {
                    ItemStack output = ComputerReciepts.getInstance().getSinteringResult(inputs[0], inputs[1]);
                    if(!output.isEmpty())
                    {
                        System.out.println(7777);
                        smelting = output;
                        cookTime++;
                        inputs[0].shrink(1);
                        inputs[1].shrink(1);
                        handler.setStackInSlot(0, inputs[0]);
                        handler.setStackInSlot(1, inputs[1]);
                        energy -= 20;
                    }
                }
            }
        }
    }
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.computer");
    }
    public int getEnergyStored()
    {
        return this.energy;
    }

    public int getMaxEnergyStored()
    {
        return this.storage.getMaxEnergyStored();
    }

    public boolean isUsableByPlayer(PlayerEntity player)
    {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public int getField(int id)
    {
        switch(id)
        {
            case 0:
                return this.cookTime;
            case 1:
                return this.energy;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch(id)
        {
            case 0:
                this.cookTime = value;
                break;
            case 1:
                this.energy = value;
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.base_computer");
    }

    @Nullable
    public Container createMenu(int id, PlayerInventory player,PlayerEntity p) {
        return new ComputerContainer(id, player, this);
    }
}
