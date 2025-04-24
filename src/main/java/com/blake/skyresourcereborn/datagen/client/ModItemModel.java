package com.blake.skyresourcereborn.datagen.client;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import java.util.Set;

import com.blake.skyresourcereborn.registry.ModBlocks;
import com.blake.skyresourcereborn.utils.DataGenUtil;
import com.blake.skyresourcereborn.utils.ResourceLocationUtil;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModel extends ItemModelProvider {

    public ModItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    // private Set<RegistryObject<Item>> blacklist = Set.of();

    @Override
    protected void registerModels() {

        //TODO to finish
    //     DataGenUtil.itemBlockwithParent(ModBlocks.MULTIBLOCK_CORE, null, ResourceLocationUtil
    //                     .getResource("block/" +
    //                             ResourceLocationUtil.getPath(Blocks.REDSTONE_BLOCK), "minecraft"));
    //     DataGenUtil.itemBlockwithParent(ModBlocks.ENERGY_INPUT, null, "");
    //     DataGenUtil.itemBlockwithParent(ModBlocks.ITEM_INPUT, null, "");
    //     DataGenUtil.itemBlockwithParent(ModBlocks.ITEM_OUTPUT, null, "");
    //    DemoBlocks(, Blocks.REDSTONE_BLOCK);
    //     DemoBlocks(ModBlocks.ENERGY_INPUT, Blocks.REDSTONE_LAMP);
    //     DemoBlocks(ModBlocks.ITEM_INPUT, Blocks.LIME_WOOL);
    //     DemoBlocks(ModBlocks.ITEM_OUTPUT, Blocks.LIGHT_BLUE_WOOL);


    }

}
