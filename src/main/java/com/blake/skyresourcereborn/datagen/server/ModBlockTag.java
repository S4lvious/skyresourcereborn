package com.blake.skyresourcereborn.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.blake.skyresourcereborn.SkyResourceReborn;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
@SuppressWarnings("null")
public class ModBlockTag extends BlockTagsProvider{

    public ModBlockTag(PackOutput o, CompletableFuture<Provider> l, ExistingFileHelper f) {
                super(o, l, SkyResourceReborn.MODID, f);
        }

    @Override
    protected void addTags(Provider p) {

    }
}
