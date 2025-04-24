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
                    JsonObject processing = json.getAsJsonObject("processing");
                    String input = processing.get("input").getAsString();
                    String output = processing.get("output").getAsString();
                    int time = processing.get("time").getAsInt();

                    MultiblockProcessing processingData = new MultiblockProcessing(input, output, time);
                    MultiblockStructure s = new MultiblockStructure(
                            id,
                            structure,
                            json.get("requires_energy").getAsBoolean(),
                            json.get("energy_per_tick").getAsInt(),
                            processingData
                    );

                    STRUCTURES.put(id, s);
                }
                System.out.println("✅ Caricate " + STRUCTURES.size() + " strutture multiblock");
            }
        });
    }
}
