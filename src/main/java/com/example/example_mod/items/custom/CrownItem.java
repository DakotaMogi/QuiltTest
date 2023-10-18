package com.example.example_mod.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;

import java.util.UUID;

public class CrownItem extends ArmorItem {

	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
	public CrownItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
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

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		return slot == this.armorSlot.getEquipmentSlot() ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}
}
