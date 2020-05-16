package com.ShapeShifter420.evmod.gui;

import com.ShapeShifter420.evmod.EVmod;
import com.mojang.blaze3d.systems.RenderSystem;


import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BaseComputerGui extends ContainerScreen<ComputerContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(EVmod.MOD_ID, "textures/gui/sintering_furnace.png");
    private final TileEntityComputer tileentity;
    public BaseComputerGui(ComputerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.tileentity=screenContainer.tileentity;
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 183;
    }

    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, 90.0f, 4210752);
        this.font.drawString(Integer.toString(this.tileentity.getEnergyStored()), 115, 72, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        int l = this.getCookProgressScaled(24);
        this.blit(this.guiLeft + 63, this.guiTop + 36, 176, 14, l + 1, 16);

        int k = this.getEnergyStoredScaled(75);
        this.blit(this.guiLeft + 152, this.guiTop + 7, 176, 32, 16, 76 - k);
    }
    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileentity.cookTime;
        return i != 0 ? i * pixels / 100 : 0;
    }

    private int getEnergyStoredScaled(int pixels)
    {
        int i = this.tileentity.getEnergyStored();
        int j = this.tileentity.getMaxEnergyStored();
        return i != 0 && j != 0 ? i * pixels / j : 0;
    }
}
