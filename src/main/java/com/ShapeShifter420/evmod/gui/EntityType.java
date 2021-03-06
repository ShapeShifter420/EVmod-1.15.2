package com.ShapeShifter420.evmod.gui;

import com.ShapeShifter420.evmod.EVmod;
import com.ShapeShifter420.evmod.block.BlockList;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityType {

    public static final  DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
    ForgeRegistries.TILE_ENTITIES, EVmod.MOD_ID);

    public static final RegistryObject<TileEntityType<TileEntityComputer>> BASE_COMPUTER = EntityType.TILE_ENTITY_TYPES
            .register("base_computer", () -> TileEntityType.Builder
            .create(TileEntityComputer::new, BlockList.BASE_COMPUTER.get()).build(null));;
    public static final RegistryObject<TileEntityType<TileEntityBase_C>> NANOCHEST = EntityType.TILE_ENTITY_TYPES
            .register("nanochest", () -> TileEntityType.Builder
                    .create(TileEntityBase_C::new, BlockList.NANOCHEST.get()).build(null));;
}
