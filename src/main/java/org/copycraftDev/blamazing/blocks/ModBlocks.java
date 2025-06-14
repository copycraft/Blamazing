package org.copycraftDev.blamazing.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.copycraftDev.blamazing.Blamazing;

public class ModBlocks {
    public static final Block BLASTPROOF_GLASS = new Block(
            FabricBlockSettings.of()
                    .strength(0.3f, 1200.0f)
                    .sounds(BlockSoundGroup.GLASS)
                    .nonOpaque()
    );

    public static void registerBlocks() {
        Registry.register(
                Registries.BLOCK,
                new Identifier(Blamazing.MOD_ID, "blastproof_glass"),
                BLASTPROOF_GLASS
        );

        Registry.register(
                Registries.ITEM,
                new Identifier(Blamazing.MOD_ID, "blastproof_glass"),
                new BlockItem(
                        BLASTPROOF_GLASS,
                        new FabricItemSettings().maxCount(128)
                )
        );
    }
}
