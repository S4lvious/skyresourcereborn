package com.blake.skyresourcereborn.datagen.client;

import static com.blake.skyresourcereborn.SkyResourceReborn.MODID;

import com.blake.skyresourcereborn.registry.ModBlocks;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class ModLang extends LanguageProvider {

    public ModLang(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {


        add(MODID + ".tab", "Sky Resources : Reborn");

        //all blockitems
        ModBlocks.BLOCKITEMS.getEntries().forEach(b -> RegistryToLang("block", b));

    }

    private void RegistryToLang(String type, RegistryObject<?> d) {
        add(type + "." + d.getId().toString().replace(":", "."),
                Named(d.getId().toString().replace(MODID + ":", "")));
    }

    private String Named(String text) {

        StringBuilder result = new StringBuilder();
        for (String word : text.replaceAll("_", " ").split(" ")) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return result.toString().trim();
    }

}
