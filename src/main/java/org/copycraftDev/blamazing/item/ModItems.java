package org.copycraftDev.blamazing.item;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import org.copycraftDev.blamazing.Blamazing;

public class ModItems {
    public static final Item BLAMACE = Registry.register(
            Registries.ITEM,
            new Identifier(Blamazing.MOD_ID, "blamace"),
            new BlamaceItem(
                    ToolMaterials.NETHERITE,  // crazy strong material
                    0,                        // extraDamage handled in postHit
                    -2.4f,                    // attack speed
                    new FabricItemSettings().maxCount(1)

            )
    );

    public static void registerItems() {
        // If you want it in a creative tab, hook it up here...
    }
}
