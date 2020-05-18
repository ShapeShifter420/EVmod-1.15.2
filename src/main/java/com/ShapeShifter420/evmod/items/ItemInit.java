package com.ShapeShifter420.evmod.items;

import com.ShapeShifter420.evmod.EVmod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


@ObjectHolder(EVmod.MOD_ID)
public class ItemInit {


    public enum ModItemTier implements IItemTier {
        // enchantability, Supplier<Ingredient> repairMaterial
        EXAMPLE(4, 1500, 15.0F, 7.0F, 250, () -> {
            return Ingredient.fromItems(ItemList.soldering_iron);
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
    public enum ModArmorMaterial implements IArmorMaterial {
        TEST(EVmod.MOD_ID + ":white_god", 50, new int[]{3, 8, 6, 3}, 50, SoundEvents.field_226124_Y_, 6.9F, () -> {
            return Ingredient.fromItems(ItemList.copper);
        });

        private static final int[] MAX_DAMAGE_ARRAY = new int[]{16, 16, 16, 16};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final LazyValue<Ingredient> repairMaterial;

        private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountIn,
                                 int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn,
                                 Supplier<Ingredient> repairMaterialIn) {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.repairMaterial = new LazyValue<>(repairMaterialIn);
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return this.damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }

//        @Override
//        public void tick(World world, PlayerEntity player, ItemStack itemStack) {
//            if (itemStack.getItem().equals(ArmorList.white_god_leggins))
//                player.addPotionEffect(new EffectInstance());
//        }

//        @Override
//        public void tick(World world, PlayerEntity player, ItemStack itemStack) {
//            if (itemStack.getItem().equals(ArmorList.white_god_leggins))
//                player.addPotionEffect(new EffectInstance());
//        }
    }
}

