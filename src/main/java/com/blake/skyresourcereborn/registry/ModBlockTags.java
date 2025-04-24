package com.blake.skyresourcereborn.registry;

import org.antlr.v4.parse.BlockSetTransformer.setAlt_return;

import com.blake.skyresourcereborn.SkyResourceReborn;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@SuppressWarnings({ "unused", "deprecation" })
public class ModBlockTags {

    public static TagKey<Block> HATCHES = tag("hatches");
    public static TagKey<Block> INPUT_HATCHES = tag("hatches/input");
    public static TagKey<Block> OUTPUT_HATCHES = tag("hatches/output");

    public static TagKey<Block> CORE = tag("core");

    /**
     * create an blocktag
     */
    private static TagKey<Block> tag(String name) {
        return tag(name, SkyResourceReborn.MODID);
    }

    /**
     * create an blocktag
     */
    private static TagKey<Block> tag(String name, String modname) {
        return TagKey.create(BuiltInRegistries.BLOCK.key(),
                ResourceLocation.fromNamespaceAndPath(modname, name));
    }
}
