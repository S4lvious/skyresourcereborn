package com.blake.skyresourcereborn.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import com.blake.skyresourcereborn.datagen.client.ModItemModel;

public class DataGenUtil {

    public static final ResourceLocation CUTOUT = ResourceLocation.withDefaultNamespace("cutout");

    private static String mc = "minecraft:";
    public static String TOOL = mc + "item/handheld";
    public static String ITEM = mc + "item/generated";
    private static String modparent = MODID + ":";

    public static ItemModelBuilder itemTool(Item item, ItemModelProvider b) {
        return b.withExistingParent(ResourceLocationUtil.getPath(item), TOOL).texture("layer0",
                ResourceLocationUtil.getResource("item/" + ResourceLocationUtil.getPath(item)));
    }

    public static ItemModelBuilder itemModel(Item item, ModItemModel b) {
        return b.withExistingParent(ResourceLocationUtil.getPath(item), ITEM).texture("layer0",
                ResourceLocationUtil.getResource("item/" + ResourceLocationUtil.getPath(item)));
    }

    public static ItemModelBuilder itemBlock(Block block, ItemModelProvider b) {
        return b.withExistingParent(ResourceLocationUtil.getPath(block),
                modparent + ResourceLocationUtil.getPath(block));
    }

    public static ItemModelBuilder itemBlockwithParent(Block block, ItemModelProvider b, String parent) {
        return b.withExistingParent(ResourceLocationUtil.getPath(block), parent);
    }

    public static ItemModelBuilder itemBlockwithParent(Block block, ItemModelProvider b, String parent, String keyname,
            String texture) {
        return b.withExistingParent(ResourceLocationUtil.getPath(block), parent).texture(keyname, texture);
    }

    /**
     * @param block
     * @param b       this
     * @param parent  MODID + ":block/..."
     * @param keyname "all"
     * @param texture "minecraft:block/cobblestone"
     * @return blockmodel
     */
    public static BlockModelBuilder BlockwithParent(Block block, BlockStateProvider b,
            String parent, String keyname, String texture) {
        return b.models().withExistingParent(ResourceLocationUtil.getPath(block), parent)
                .texture(keyname, texture);
    }

    /**
     * 
     * @param block
     * @param b
     * @param parent
     * @return blockmodel
     */
    public static BlockModelBuilder BlockwithParent(Block block, BlockStateProvider b,
            String parent) {
        return b.models().withExistingParent(ResourceLocationUtil.getPath(block), parent);
    }

    /**
     * @return blockstate and model
     */
    public static void simpleBlockwithModel(BlockStateProvider d, Block b, String parent) {
        d.simpleBlock(b, BlockwithParent(b, d, parent));
    }

    /**
     * @return blockstate and model
     */
    public static void simpleBlockwithModel(BlockStateProvider d, Block b, String parent, String keyname,
            String texture) {
        d.simpleBlock(b, BlockwithParent(b, d, parent, keyname, texture));
    }

}
