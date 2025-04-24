package com.blake.skyresourcereborn.registry;

import java.util.function.Supplier;

import com.blake.skyresourcereborn.SkyResourceReborn;
import com.blake.skyresourcereborn.block.ItemInputBlock;
import com.blake.skyresourcereborn.block.ItemOutputBlock;
import com.blake.skyresourcereborn.block.MultiblockCoreBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

        public static void register(IEventBus bus) {
                BLOCKS.register(bus);
                BLOCKITEMS.register(bus);
                ITEMS.register(bus);
        }

        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        SkyResourceReborn.MODID);

        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        SkyResourceReborn.MODID);

        public static final DeferredRegister<Item> BLOCKITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        SkyResourceReborn.MODID);

        public static final RegistryObject<Block> MULTIBLOCK_CORE = registerItemBlock("multiblock_core",
                        MultiblockCoreBlock::new);

        // public static final RegistryObject<Item> MULTIBLOCK_CORE_ITEM =
        // ITEMS.register("multiblock_core",
        // () -> new BlockItem(MULTIBLOCK_CORE.get(), new Item.Properties()));

        public static final RegistryObject<Block> ITEM_INPUT = registerItemBlock("item_input",
                        ItemInputBlock::new);

        // public static final RegistryObject<Item> ITEM_INPUT_ITEM =
        // ITEMS.register("item_input",
        // () -> new BlockItem(ITEM_INPUT.get(), new Item.Properties()));

        public static final RegistryObject<Block> ITEM_OUTPUT = registerItemBlock("item_output",
                        ItemOutputBlock::new);

        // public static final RegistryObject<Item> ITEM_OUTPUT_ITEM =
        // ITEMS.register("item_output",
        // () -> new BlockItem(ITEM_OUTPUT.get(), new Item.Properties()));

        public static final RegistryObject<Block> ENERGY_INPUT = registerItemBlock("energy_input",
                        () -> new Block(Block.Properties.of().strength(1.0f)));

        // public static final RegistryObject<Item> ENERGY_INPUT_ITEM =
        // ITEMS.register("energy_input",
        // () -> new BlockItem(ENERGY_INPUT.get(), new Item.Properties()));

        private static RegistryObject<Block> registerItemBlock(String name, Supplier<? extends Block> s) {
                RegistryObject<Block> block = BLOCKS.register(name, s);
                BLOCKITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
                return block;
        }
}
