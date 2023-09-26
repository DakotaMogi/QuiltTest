package com.example.example_mod.items;

import com.example.example_mod.DakotasTests;
import com.example.example_mod.items.custom.HammerItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ModItems {

	public static Item hammerItem = RegisterItem("hammer", new HammerItem(ToolMaterials.NETHERITE, 8, -3.1f, new QuiltItemSettings().maxCount(1)));


	private static Item RegisterItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(DakotasTests.MOD_ID, name), item);
	}

	public static void RegisterModItems(){
		DakotasTests.LOGGER.info("Registering mod items for " + DakotasTests.MOD_ID);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addItem(hammerItem));
	}
}
