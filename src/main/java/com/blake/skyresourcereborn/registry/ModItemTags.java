package com.blake.skyresourcereborn.registry;

import org.antlr.v4.parse.BlockSetTransformer.setAlt_return;

import com.blake.skyresourcereborn.SkyResourceReborn;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

@SuppressWarnings({ "unused", "deprecation" })
public class ModItemTags {

    public static TagKey<Item> HATCHES = tag("hatches");
    public static TagKey<Item> INPUT_HATCHES = tag("hatches/input");
    public static TagKey<Item> OUTPUT_HATCHES = tag("hatches/output");

    public static TagKey<Item> CORE = tag("core");

    /**
     * create an itemtag
     */
    private static TagKey<Item> tag(String name) {
        return tag(name, SkyResourceReborn.MODID);
    }

    /**
     * create an itemtag
     */
    private static TagKey<Item> tag(String name, String modname) {
        return TagKey.create(BuiltInRegistries.ITEM.key(),
                ResourceLocation.fromNamespaceAndPath(modname, name));
    }
}
