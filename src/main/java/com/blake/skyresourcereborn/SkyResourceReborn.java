package com.blake.skyresourcereborn;

import com.blake.skyresourcereborn.client.screen.MultiblockCoreScreen;
import com.blake.skyresourcereborn.registry.ModBlockEntities;
import com.blake.skyresourcereborn.client.screen.ItemInputScreen;
import com.blake.skyresourcereborn.client.screen.ItemOutputScreen;
import com.blake.skyresourcereborn.multiblock.MultiblockManager;
import com.blake.skyresourcereborn.registry.ModBlocks;
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
public class SkyResourceReborn {

    public static final String MODID = "skyresourcereborn";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SkyResourceReborn() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(MultiblockManager.class);
        ModMenus.MENUS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }



    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenus.ITEM_INPUT_MENU.get(), ItemInputScreen::new);
            MenuScreens.register(ModMenus.ITEM_OUTPUT_MENU.get(), ItemOutputScreen::new);
            MenuScreens.register(ModMenus.MULTIBLOCK_CORE_MENU.get(), MultiblockCoreScreen::new);

        }
    }
}
