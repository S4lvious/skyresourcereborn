package com.blake.skyresourcereborn.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.blake.skyresourcereborn.SkyResourceReborn;
import com.blake.skyresourcereborn.registry.*;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")
public class ModBlockTag extends BlockTagsProvider {

    public ModBlockTag(PackOutput o, CompletableFuture<Provider> l, ExistingFileHelper f) {
        super(o, l, SkyResourceReborn.MODID, f);
    }

    @Override
    protected void addTags(Provider p) {
        tag(ModBlockTags.CORE).add(
                ModBlocks.MULTIBLOCK_CORE.get());

        tag(ModBlockTags.HATCHES).add(
                ModBlocks.ENERGY_INPUT.get(),
                ModBlocks.ITEM_INPUT.get(),
                ModBlocks.ITEM_OUTPUT.get());

        tag(ModBlockTags.INPUT_HATCHES).add(
                ModBlocks.ENERGY_INPUT.get(),
                ModBlocks.ITEM_INPUT.get());

        tag(ModBlockTags.OUTPUT_HATCHES).add(
                ModBlocks.ITEM_OUTPUT.get());
    }
}
