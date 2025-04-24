package com.blake.skyresourcereborn.registry;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {

    public static void register(IEventBus bus) {
        CREATIVE_TAB.register(bus);
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> CREATIVETAB = CREATIVE_TAB
            .register(MODID, () -> CreativeModeTab.builder()
                    .title(Component.translatable(MODID + ".tab"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> ModBlocks.MULTIBLOCK_CORE.get().asItem().getDefaultInstance())
                    .displayItems((parameters, output) -> {

                        // blocks
                        ModBlocks.BLOCKITEMS.getEntries().forEach(e -> output.accept((Item) e.get()));

                    }).build());

}
