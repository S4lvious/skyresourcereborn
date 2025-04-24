package com.blake.skyresourcereborn.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class MultiblockStructure {

    public record BlockOffset(BlockPos offset, ResourceLocation blockId) {}

    private final ResourceLocation id;
    private final List<BlockOffset> structure;
    private final boolean requiresEnergy;
    private final int energyPerTick;
    private final MultiblockProcessing processing;

    public MultiblockStructure(ResourceLocation id, List<BlockOffset> structure,
                               boolean requiresEnergy, int energyPerTick,
                               MultiblockProcessing processing) {
        this.id = id;
        this.structure = structure;
        this.requiresEnergy = requiresEnergy;
        this.energyPerTick = energyPerTick;
        this.processing = processing;
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

    public MultiblockProcessing processing() {
        return processing;
    }
}
