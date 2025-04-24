package com.blake.skyresourcereborn.registry;

import com.blake.skyresourcereborn.SkyResourceReborn;
import com.blake.skyresourcereborn.blockentity.ItemInputBE;
import com.blake.skyresourcereborn.blockentity.ItemOutputBE;
import com.blake.skyresourcereborn.blockentity.MultiblockCoreBE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SkyResourceReborn.MODID);

    public static final RegistryObject<BlockEntityType<MultiblockCoreBE>> MULTIBLOCK_CORE_BE =
            BLOCK_ENTITIES.register("multiblock_core_be",
                    () -> BlockEntityType.Builder.of(MultiblockCoreBE::new, ModBlocks.MULTIBLOCK_CORE.get()).build(null));

    public static final RegistryObject<BlockEntityType<ItemInputBE>> ITEM_INPUT_BE =
            BLOCK_ENTITIES.register("item_input_be",
                    () -> BlockEntityType.Builder.of(ItemInputBE::new, ModBlocks.ITEM_INPUT.get()).build(null));

    public static final RegistryObject<BlockEntityType<ItemOutputBE>> ITEM_OUTPUT_BE =
            BLOCK_ENTITIES.register("item_output_be",
                    () -> BlockEntityType.Builder.of(ItemOutputBE::new, ModBlocks.ITEM_OUTPUT.get()).build(null));


}
