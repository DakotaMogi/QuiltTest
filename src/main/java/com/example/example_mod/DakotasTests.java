package com.example.example_mod;

import com.example.example_mod.items.ModItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DakotasTests implements ModInitializer {
	public static final String MOD_ID = "dakotas_tests";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		ModItems.RegisterModItems();
	}
}
