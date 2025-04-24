package com.blake.skyresourcereborn.datagen.client;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import com.blake.skyresourcereborn.registry.ModBlocks;
import com.blake.skyresourcereborn.utils.ResourceLocationUtil;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockModelState extends BlockStateProvider {

    public ModBlockModelState(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // demo blockstate and models
        DemoBlocks(ModBlocks.MULTIBLOCK_CORE, Blocks.REDSTONE_BLOCK);
        DemoBlocks(ModBlocks.ENERGY_INPUT, Blocks.REDSTONE_LAMP);
        DemoBlocks(ModBlocks.ITEM_INPUT, Blocks.LIME_WOOL);
        DemoBlocks(ModBlocks.ITEM_OUTPUT, Blocks.LIGHT_BLUE_WOOL);

    }

    private void DemoBlocks(RegistryObject<Block> block, Block copy) {
        simpleBlock(block.get(),
                models().getExistingFile(ResourceLocationUtil
                        .getResource("block/" +
                                ResourceLocationUtil.getPath(copy), "minecraft")));
    }

}
