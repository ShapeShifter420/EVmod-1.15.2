package com.ShapeShifter420.evmod.items;

import com.ShapeShifter420.evmod.EVmod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ArmorInit extends ArmorItem {
    // Вот это вот все я считаю ебаный костыль ибо похорошему мы должны наследоваться от симпл итем инит
    // Проблема в том что я ебланул на первом этапе и поставил добовление в итемлист, ибо блоки
    // Поэтому для того чтобы код был расширяем для армор итемов приходиться отдельно регать симпл итем
    // Возможно если будем поддержовать мод дальше то стоит преписать добовление более эффективно
    public ArmorInit(String name, EquipmentSlotType slot_equipment) {
        super(ItemInit.ModArmorMaterial.TEST,
                slot_equipment,
                new Item.Properties().group(EVmod.EVItemGroup.instance));
        this.setRegistryName(name);
        //super(new ArmorItem(ItemInit.ModArmorMaterial.TEST, slot_equipment, new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName(name));
        ArmorList.ArmorItems.add(this);
    }

}
