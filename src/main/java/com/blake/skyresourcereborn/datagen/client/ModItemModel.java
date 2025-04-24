package com.blake.skyresourcereborn.datagen.client;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import com.blake.skyresourcereborn.registry.ModBlocks;
import com.blake.skyresourcereborn.utils.ResourceLocationUtil;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModel extends ItemModelProvider {

    public ModItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    // private Set<RegistryObject<Item>> blacklist = Set.of();

    @Override
    protected void registerModels() {

        copy(ModBlocks.MULTIBLOCK_CORE.get(),Blocks.REDSTONE_BLOCK);
        copy(ModBlocks.ENERGY_INPUT.get(), Blocks.REDSTONE_LAMP);
        copy(ModBlocks.ITEM_INPUT.get(), Blocks.LIME_WOOL);
        copy(ModBlocks.ITEM_OUTPUT.get(), Blocks.LIGHT_BLUE_WOOL);


    }

    private void copy(Block base,Block copy){
        this.withExistingParent(ResourceLocationUtil.getPath(base),
                 "minecraft:block/" + ResourceLocationUtil.getPath(copy));
    }

}
