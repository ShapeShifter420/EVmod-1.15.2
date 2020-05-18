package com.ShapeShifter420.evmod.block;

import com.ShapeShifter420.evmod.EVmod;
import com.ShapeShifter420.evmod.objects.ComputerBlock;
import net.minecraft.block.Block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BlockList {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
            EVmod.MOD_ID);
    /* Два листа один для блоков в мире другой для блоков в инвентаре
    * блок инит должен добавлять сначала в блок а потом и в блок итемс
    * смотри реализацию блок инит оре для подробностей */
    public static List<Block> Blocks = new ArrayList<Block>();
    public static List<BlockItem> BlockItems = new ArrayList<BlockItem>();
    public static Block ore_copper = new BlockInitOre("ore_copper", 1.5F);
    public static Block ore_wolfram = new BlockInitOre("ore_wolfram", 1.5F);
    //public static ComputerBlock base_computer = new BlockInitComputer("base_computer", 1.5F);
    public static Block monitor = new BlockInit("monitor", 1.5F);

    //public static Block mk2_controller = new BlockInit("mk2_controller", 1.5F);
    public static Block mk2_recorder = new BlockInit("mk2_recorder", 1.5F);
    public static Block mk2_up = new BlockInit("mk2_up", 1.5F);
    public static Block mk2_down = new BlockInit("mk2_down", 1.5F);

    public static Block mk3_0_0 = new BlockInit("mk3_0_0", 1.5F);
    public static Block mk3_0_1 = new BlockInit("mk3_0_1", 1.5F);
    public static Block mk3_0_2 = new BlockInit("mk3_0_2", 1.5F);
    public static Block mk3_1_0 = new BlockInit("mk3_1_0", 1.5F);
    public static Block mk3_1_1 = new BlockInit("mk3_1_1", 1.5F);
    public static Block mk3_1_2 = new BlockInit("mk3_1_2", 1.5F);
    public static Block mk3_2_0 = new BlockInit("mk3_2_0", 1.5F);
    public static Block mk3_2_1 = new BlockInit("mk3_2_1", 1.5F);
    public static Block mk3_2_2 = new BlockInit("mk3_2_2", 1.5F);

    public static Block power_block = new BlockInit("power_block",1.5F);
    public static Block chip_block= new BlockInit("chip_block",1.5F);
    public static final RegistryObject<Block> BASE_COMPUTER = BLOCKS.register("mk2-controller",
            () -> new ComputerBlock(Block.Properties.from(new Block(Block.Properties.create(Material.IRON)))));
}
