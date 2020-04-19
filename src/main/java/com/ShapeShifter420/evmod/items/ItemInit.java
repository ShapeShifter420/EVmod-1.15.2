package com.ShapeShifter420.evmod.items;

import com.ShapeShifter420.evmod.EVmod;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(modid = EVmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EVmod.MOD_ID)
public class ItemInit {
    public static final Item controller = null;
    public static final Item copper = null;
    public static final Item plastic = null;
    public static final Item punch_card = null;
    public static final Item bitcoin = null;
    public static final Item wolfram = null;
    public static final Item chip = null;
    public static final Item battery = null;
    public static final Item soldering_iron = null;
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry()
                .register(new Item(new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName("controller"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName("copper"));
        event.getRegistry()
            .register(new Item(new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName("plastic"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName("punch_card"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName("bitcoin"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName("wolfram"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName("chip"));
        event.getRegistry()
                .register(new Item(new Item.Properties().group(EVmod.EVItemGroup.instance)).setRegistryName("battery"));
        //Tool
        event.getRegistry().register(
                new PickaxeItem(ModItemTier.EXAMPLE, 4, 5.0f, new Item.Properties().group(EVmod.EVItemGroup.instance))
                        .setRegistryName("soldering_iron"));
    }
    public enum ModItemTier implements IItemTier {
        // enchantability, Supplier<Ingredient> repairMaterial
        EXAMPLE(4, 1500, 15.0F, 7.0F, 250, () -> {
            return Ingredient.fromItems(ItemInit.soldering_iron);
        });

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;

        private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability,
                            Supplier<Ingredient> repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }

        @Override
        public int getMaxUses() {
            return this.maxUses;
        }

        @Override
        public float getEfficiency() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }
    }
}

