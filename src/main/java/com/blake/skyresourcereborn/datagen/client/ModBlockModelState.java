package com.blake.skyresourcereborn.datagen.client;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelState extends BlockStateProvider {

    public ModBlockModelState(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

}
