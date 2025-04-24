package com.blake.skyresourcereborn.registry;

import com.blake.skyresourcereborn.SkyResourceReborn;
import com.blake.skyresourcereborn.menu.ItemInputMenu;
import com.blake.skyresourcereborn.menu.ItemOutputMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, SkyResourceReborn.MODID);

    public static final RegistryObject<MenuType<ItemInputMenu>> ITEM_INPUT_MENU =
            MENUS.register("item_input_menu",
                    () -> IForgeMenuType.create((windowId, inv, buf) -> new ItemInputMenu(windowId, inv, buf)));

    public static final RegistryObject<MenuType<ItemOutputMenu>> ITEM_OUTPUT_MENU =
            MENUS.register("item_output_menu",
                    () -> IForgeMenuType.create((id, inv, buf) -> new ItemOutputMenu(id, inv, buf)));


}
