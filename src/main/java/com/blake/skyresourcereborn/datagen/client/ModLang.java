package com.blake.skyresourcereborn.datagen.client;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLang extends LanguageProvider {

    public ModLang(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

    }

}
