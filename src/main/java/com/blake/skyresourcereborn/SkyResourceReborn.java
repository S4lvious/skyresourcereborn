package com.blake.skyresourcereborn;

import com.blake.skyresourcereborn.registry.ModBlockEntities;
import com.blake.skyresourcereborn.client.screen.ItemInputScreen;
import com.blake.skyresourcereborn.client.screen.ItemOutputScreen;
import com.blake.skyresourcereborn.multiblock.MultiblockManager;
import com.blake.skyresourcereborn.registry.ModBlocks;
import com.blake.skyresourcereborn.registry.ModCreativeTab;
import com.blake.skyresourcereborn.registry.ModMenus;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SkyResourceReborn.MODID)
@SuppressWarnings({ "unused", "removal" })
public class SkyResourceReborn {

    public static final String MODID = "skyresourcereborn";

    private static final Logger LOGGER = LogUtils.getLogger();

    public SkyResourceReborn() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Registrazioni di blocchi e item (spostate in ModBlocks)
        // ModBlocks.BLOCKS.register(modEventBus); // unificati in un'unico DeferredRegister.register(bus)
        // ModBlocks.ITEMS.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTab.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus); // ✔️ ci deve stare
        // modEventBus.addListener(this::commonSetup);
        // modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(MultiblockManager.class);
        ModMenus.MENUS.register(modEventBus);
        // MinecraftForge.EVENT_BUS.register(this);
    }

    // private void commonSetup(final FMLCommonSetupEvent event) {
    // LOGGER.info("HELLO FROM COMMON SETUP");
    // }

    // private void addCreative(BuildCreativeModeTabContentsEvent event) {
    // }

    // @SubscribeEvent
    // public void onServerStarting(ServerStartingEvent event) {
    // LOGGER.info("HELLO from server starting");
    // }

}
