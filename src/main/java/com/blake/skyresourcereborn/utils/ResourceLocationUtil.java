package com.blake.skyresourcereborn.utils;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("deprecation")
public class ResourceLocationUtil {

    public static Block getBlock(String id) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, id));
    }

    public static String getPath(Block b) {
        return BuiltInRegistries.BLOCK.getKey(b).getPath();
    }

    public static String getPath(Item i) {
        return BuiltInRegistries.ITEM.getKey(i).getPath();
    }

    public static ResourceLocation getResource(String s) {
        return ResourceLocation.fromNamespaceAndPath(MODID, s);
    }

    public static ResourceLocation getResource(Block b) {
        return ResourceLocation.fromNamespaceAndPath(MODID, getPath(b));
    }

    public static ResourceLocation getResource(Item i) {
        return ResourceLocation.fromNamespaceAndPath(MODID, getPath(i));
    }

    public static ResourceLocation getResource(String s, String modname) {
        return ResourceLocation.fromNamespaceAndPath(modname, s);
    }

    public static ResourceLocation getResource(Block b, String modname) {
        return ResourceLocation.fromNamespaceAndPath(modname, getPath(b));
    }

    public static ResourceLocation getResource(Item i, String modname) {
        return ResourceLocation.fromNamespaceAndPath(modname, getPath(i));
    }
}
