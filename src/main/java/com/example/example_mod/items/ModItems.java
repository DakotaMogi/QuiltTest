package com.example.example_mod.items;

import com.example.example_mod.DakotasTests;
import com.example.example_mod.items.custom.CrownItem;
import com.example.example_mod.items.custom.HammerItem;
import com.example.example_mod.items.custom.HornedCrownItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ModItems {

	public static Item hammerItem = RegisterItem("hammer", new HammerItem(ToolMaterials.IRON, 5, -3.1f, new QuiltItemSettings().maxCount(1)));
	public static Item leatherCrownItem = RegisterItem("leather_crown", new CrownItem(ArmorMaterials.LEATHER, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item ironCrownItem = RegisterItem("iron_crown", new CrownItem(ArmorMaterials.IRON, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item goldCrownItem = RegisterItem("gold_crown", new CrownItem(ArmorMaterials.GOLD, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item diamondCrownItem = RegisterItem("diamond_crown", new CrownItem(ArmorMaterials.DIAMOND, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item netheriteCrownItem = RegisterItem("netherite_crown", new CrownItem(ArmorMaterials.NETHERITE, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item leatherHornedCrownItem = RegisterItem("leather_horned_crown", new HornedCrownItem(ArmorMaterials.LEATHER, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item ironHornedCrownItem = RegisterItem("iron_horned_crown", new HornedCrownItem(ArmorMaterials.IRON, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item goldHornedCrownItem = RegisterItem("gold_horned_crown", new HornedCrownItem(ArmorMaterials.GOLD, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item diamondHornedCrownItem = RegisterItem("diamond_horned_crown", new HornedCrownItem(ArmorMaterials.DIAMOND, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static Item netheriteHornedCrownItem = RegisterItem("netherite_horned_crown", new HornedCrownItem(ArmorMaterials.NETHERITE, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));


	private static Item RegisterItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(DakotasTests.MOD_ID, name), item);
	}

	public static void RegisterModItems(){
		DakotasTests.LOGGER.info("Registering mod items for " + DakotasTests.MOD_ID);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(hammerItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(leatherCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(leatherHornedCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(ironCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(ironHornedCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(goldCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(goldHornedCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(diamondCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(diamondHornedCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(netheriteCrownItem));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(netheriteHornedCrownItem));
	}
}
