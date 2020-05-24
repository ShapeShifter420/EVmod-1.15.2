package com.ShapeShifter420.evmod.items;


import com.ShapeShifter420.evmod.EVmod;
import com.ShapeShifter420.evmod.tools.SolderinIronTool;
import com.ShapeShifter420.evmod.tools.WiresStick;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    public static List<Item> Items = new ArrayList<Item>();
    public static Item controller = new SimpleItemInit("controller");
    public static Item radio_lamp = new SimpleItemInit("radio_lamp");
    public static Item copper = new SimpleItemInit("copper");
    public static Item plastic = new SimpleItemInit("plastic");
    public static Item punch_card = new SimpleItemInit("punch_card");
    public static Item bitcoin = new SimpleItemInit("bitcoin");
    public static Item wolfram = new SimpleItemInit("wolfram");
    public static Item chip = new SimpleItemInit("chip");
    public static Item battery = new SimpleItemInit("battery");
    public static Item soldering_iron= new SolderinIronTool(ItemInit.ModItemTier.EXAMPLE, 4, 5.0f, new Item.Properties().group(EVmod.EVItemGroup.instance))
            .setRegistryName("soldering_iron");
   public static WiresStick wiresstick = new WiresStick("wiresstick");
}
