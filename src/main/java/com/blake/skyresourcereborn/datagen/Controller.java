package com.blake.skyresourcereborn.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.blake.skyresourcereborn.SkyResourceReborn;
import com.blake.skyresourcereborn.datagen.client.*;
import com.blake.skyresourcereborn.datagen.server.*;

@Mod.EventBusSubscriber(modid = SkyResourceReborn.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Controller {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator g = e.getGenerator();
        PackOutput po = g.getPackOutput();
        ExistingFileHelper f = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> pr = e.getLookupProvider();

        // client

        providerGen(e, g, new ModBlockModelState(po, f));
        providerGen(e, g, new ModItemModel(po, f));
        providerGen(e, g, new ModLang(po));

        // server
        ModBlockTag blocktag = new ModBlockTag(po, pr, f);
        providerGen(e, g, blocktag);
        providerGen(e, g, new ModItemTag(po, pr, blocktag.contentsGetter()));
        providerGen(e, g, new ModLoot(po));
        providerGen(e, g, new ModRecipe(po));
        // providerGen(e, g, new DataFluidTag(po, pr, f)); not yet used

    }

    private static <T extends DataProvider> void providerGen(GatherDataEvent e, DataGenerator g, T f) {
        g.addProvider(e.includeClient(), f);
    }

}