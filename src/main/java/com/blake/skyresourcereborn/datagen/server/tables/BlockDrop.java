package com.blake.skyresourcereborn.datagen.server.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.blake.skyresourcereborn.registry.ModBlocks;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class BlockDrop extends BlockLootSubProvider {

        public BlockDrop() {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                List<Block> blocks = new ArrayList<>();
                ModBlocks.BLOCKS.getEntries().forEach(e -> blocks.add(e.get()));
                return blocks;
        }

        @Override
        protected void generate() {
                try {
                        ModBlocks.BLOCKS.getEntries().forEach(e -> dropSelf(e.get()));
                } catch (Exception e) {
                        //missing blocks on getKnownBlocks()
                }

        }

}