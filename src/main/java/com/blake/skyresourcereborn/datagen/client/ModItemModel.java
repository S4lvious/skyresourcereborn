package com.blake.skyresourcereborn.datagen.client;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModel extends ItemModelProvider {

    public ModItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }


    @Override
    protected void registerModels() {

    }

}
