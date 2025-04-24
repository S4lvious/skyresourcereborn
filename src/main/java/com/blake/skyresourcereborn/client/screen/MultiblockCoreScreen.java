package com.blake.skyresourcereborn.client.screen;

import com.blake.skyresourcereborn.menu.MultiblockCoreMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MultiblockCoreScreen extends AbstractContainerScreen<MultiblockCoreMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("skyresourcereborn", "textures/gui/multiblock_core_gui.png");

    public MultiblockCoreScreen(MultiblockCoreMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        graphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 176, 166);

        // 🔁 Barra progresso: calcolo avanzamento
        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();

        if (maxProgress > 0 && progress > 0) {
            int progressPixels = (int) ((progress / (float) maxProgress) * 24); // 24px è la larghezza piena della barra
            graphics.blit(TEXTURE, leftPos + 76, topPos + 34, 176, 0, progressPixels, 16); // coords GUI, coords nella texture, size
        }
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(font, this.title, 8, 6, 0x404040);
        graphics.drawString(font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 0x404040);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}
