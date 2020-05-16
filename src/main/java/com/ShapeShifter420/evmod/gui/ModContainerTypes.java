package com.ShapeShifter420.evmod.gui;

import com.ShapeShifter420.evmod.EVmod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(
            ForgeRegistries.CONTAINERS, EVmod.MOD_ID);

    public static final RegistryObject<ContainerType<ComputerContainer>> EXAMPLE_CHEST = CONTAINER_TYPES
            .register("base_computer", () -> IForgeContainerType.create(ComputerContainer::new));
}
