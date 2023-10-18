package com.example.example_mod.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registry;
import net.minecraft.world.World;

import java.util.UUID;

public class HornedCrownItem extends ArmorItem {

	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
	public HornedCrownItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
		super(material, slot, settings);
		float protection = (material.getProtection(slot) - 1);
		float toughness = material.getToughness();
		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(
			EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), "Armor modifier", protection, EntityAttributeModifier.Operation.ADDITION)
		);
		builder.put(
			EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
			new EntityAttributeModifier(UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), "Armor toughness", toughness, EntityAttributeModifier.Operation.ADDITION)
		);
		if (material == ArmorMaterials.NETHERITE) {
			builder.put(
				EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
				new EntityAttributeModifier(UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), "Armor knockback resistance", this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION)
			);
		}
		attributeModifiers = builder.build();
	}

	public boolean HasCrown(LivingEntity entity){
		Item item = entity.getEquippedStack(EquipmentSlot.HEAD).getItem();
		return item instanceof HornedCrownItem;
    }

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (!world.isClient){
			if (entity instanceof LivingEntity lEntity && HasCrown(lEntity)){
				lEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 5, 0, false, false, false), lEntity);
			}
		}
		super.inventoryTick(stack, world, entity, slot, selected);
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		return slot == this.armorSlot.getEquipmentSlot() ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}
}
