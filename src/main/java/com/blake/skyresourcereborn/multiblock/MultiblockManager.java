package com.blake.skyresourcereborn.multiblock;

import com.google.gson.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = "skyresourcereborn")
public class MultiblockManager {
    public static final Map<ResourceLocation, MultiblockStructure> STRUCTURES = new HashMap<>();

    @SubscribeEvent
    public static void onReload(AddReloadListenerEvent event) {
        event.addListener(new SimpleJsonResourceReloadListener(new Gson(), "multiblocks") {
            @Override
            protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
                STRUCTURES.clear();
                for (var entry : jsonMap.entrySet()) {
                    ResourceLocation id = entry.getKey();
                    JsonObject json = entry.getValue().getAsJsonObject();

                    List<MultiblockStructure.BlockOffset> structure = new ArrayList<>();
                    JsonArray array = json.getAsJsonArray("structure");
                    for (JsonElement element : array) {
                        JsonObject obj = element.getAsJsonObject();
                        JsonArray offsetArr = obj.getAsJsonArray("offset");
                        BlockPos offset = new BlockPos(
                                offsetArr.get(0).getAsInt(),
                                offsetArr.get(1).getAsInt(),
                                offsetArr.get(2).getAsInt()
                        );
                        ResourceLocation blockId = ResourceLocation.parse(obj.get("block").getAsString());
                        structure.add(new MultiblockStructure.BlockOffset(offset, blockId));
                    }

                    List<MultiblockStructure.Recipe> recipes = new ArrayList<>();
                    JsonArray processingArray = json.getAsJsonArray("processing");
                    for (JsonElement element : processingArray) {
                        JsonObject recipeJson = element.getAsJsonObject();
                        recipes.add(new MultiblockStructure.Recipe(
                                recipeJson.get("input").getAsString(),
                                recipeJson.get("output").getAsString(),
                                recipeJson.get("time").getAsInt()
                        ));
                    }

                    MultiblockStructure s = new MultiblockStructure(
                            id,
                            structure,
                            json.get("requires_energy").getAsBoolean(),
                            json.get("energy_per_tick").getAsInt(),
                            recipes
                    );

                    STRUCTURES.put(id, s);
                }
            }
        });
    }
}
