package com.ShapeShifter420.evmod.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;

import java.util.ArrayList;
import java.util.List;

public class ArmorList {
    public static List<ArmorItem> ArmorItems = new ArrayList<ArmorItem>();
    public static ArmorItem white_god_helmet = new ArmorInit("white_god_helmet", EquipmentSlotType.HEAD);
    public static ArmorItem white_god_chastplate = new ArmorInit("white_god_chastplate", EquipmentSlotType.CHEST);
    public static ArmorItem white_god_leggins =new ArmorInit("white_god_leggins", EquipmentSlotType.LEGS);
    public static ArmorItem white_god_boots = new ArmorInit("white_god_boots", EquipmentSlotType.FEET);
}
