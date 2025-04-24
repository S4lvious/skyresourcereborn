package com.blake.skyresourcereborn.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class MultiblockStructure {

    public record BlockOffset(BlockPos offset, ResourceLocation blockId) {}

    public record Recipe(String input, String output, int time) {}

    private final ResourceLocation id;
    private final List<BlockOffset> structure;
    private final boolean requiresEnergy;
    private final int energyPerTick;
    private final List<Recipe> recipes;

    public MultiblockStructure(ResourceLocation id, List<BlockOffset> structure,
                               boolean requiresEnergy, int energyPerTick,
                               List<Recipe> recipes) {
        this.id = id;
        this.structure = structure;
        this.requiresEnergy = requiresEnergy;
        this.energyPerTick = energyPerTick;
        this.recipes = recipes;
    }

    public ResourceLocation id() {
        return id;
    }

    public List<BlockOffset> structure() {
        return structure;
    }

    public boolean requiresEnergy() {
        return requiresEnergy;
    }

    public int energyPerTick() {
        return energyPerTick;
    }

    public List<Recipe> recipes() {
        return recipes;
    }
}
