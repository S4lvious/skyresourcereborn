package com.blake.skyresourcereborn.client;

import com.blake.skyresourcereborn.SkyResourceReborn;
import com.blake.skyresourcereborn.client.screen.ItemInputScreen;
import com.blake.skyresourcereborn.client.screen.ItemOutputScreen;
import com.blake.skyresourcereborn.registry.ModMenus;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SkyResourceReborn.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ClientRegistry {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ModMenus.ITEM_INPUT_MENU.get(), ItemInputScreen::new);
        MenuScreens.register(ModMenus.ITEM_OUTPUT_MENU.get(), ItemOutputScreen::new);

    }

}
