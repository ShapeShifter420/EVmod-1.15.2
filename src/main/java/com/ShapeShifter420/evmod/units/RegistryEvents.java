package com.ShapeShifter420.evmod.units;

import com.ShapeShifter420.evmod.EVmod;
import com.ShapeShifter420.evmod.block.BlockList;
import com.ShapeShifter420.evmod.block.SimpleBlockItemInit;
import com.ShapeShifter420.evmod.items.ArmorList;
import com.ShapeShifter420.evmod.items.ItemInit;
import com.ShapeShifter420.evmod.items.ItemList;
import com.ShapeShifter420.evmod.tools.SolderinIronTool;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.Logger;
// import java.util.logging.Logger;

@Mod.EventBusSubscriber(modid = EVmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    public static final Logger LOGGER = EVmod.LOGGER;
    public static final String MOD_ID = EVmod.MOD_ID;

    @SubscribeEvent
    public static void registerItem(final RegistryEvent.Register<Item> event) {
        for (Item item_one : ItemList.Items){
            event.getRegistry().register(item_one);
        }
        for (ArmorItem armoritem_one : ArmorList.ArmorItems){
            event.getRegistry().register(armoritem_one);
        }

        event.getRegistry().register(ItemList.soldering_iron);
        event.getRegistry().register(ItemList.wiresstick);

        for (BlockItem block_item_one : BlockList.BlockItems){
            event.getRegistry().register(block_item_one);
        }
    }
    @SubscribeEvent
    public static void registerBlock(final RegistryEvent.Register<Block> event) {
        for (Block Block_one : BlockList.Blocks) {
            event.getRegistry().register(Block_one);
        }
        BlockItem base_computer = new SimpleBlockItemInit(BlockList.BASE_COMPUTER.get(),"base_computer");
        BlockList.BlockItems.add(base_computer);
        BlockItem nanochest = new SimpleBlockItemInit(BlockList.NANOCHEST.get(),"nanochest");
        BlockList.BlockItems.add(nanochest);
    }
}
