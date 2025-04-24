package com.blake.skyresourcereborn.datagen.server;

import java.util.List;
import java.util.Set;
import com.blake.skyresourcereborn.datagen.server.tables.BlockDrop;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class ModLoot extends LootTableProvider {

    public ModLoot(PackOutput o) {
        super(o, Set.of(),
                List.of(
                        // list of any loot table entries inside ./server/tables/
                        new LootTableProvider.SubProviderEntry(BlockDrop::new, LootContextParamSets.BLOCK)));
    }

}