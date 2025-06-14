package org.copycraftDev.blamazing;

import net.fabricmc.api.ModInitializer;
import org.copycraftDev.blamazing.blocks.ModBlocks;
import org.copycraftDev.blamazing.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blamazing implements ModInitializer {
    public static final String MOD_ID = "blamazing";

    public static final Logger LOGGER = LoggerFactory.getLogger(Blamazing.class);

    @Override
    public void onInitialize() {
        LOGGER.info("Blamazing mod initializing");
        ModItems.registerItems();
        ModBlocks.registerBlocks();
    }
}
