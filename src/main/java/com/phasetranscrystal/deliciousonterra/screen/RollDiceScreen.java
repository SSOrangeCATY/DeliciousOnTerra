package com.phasetranscrystal.deliciousonterra.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import com.mojang.blaze3d.systems.RenderSystem;
import com.phasetranscrystal.deliciousonterra.DeliciousOnTerra;
import com.phasetranscrystal.deliciousonterra.dnd.AddonDiceSystem;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class RollDiceScreen extends Screen {

    public final AddonDiceSystem system;
    public static final ResourceLocation TEXTURE = ResourceLocation.tryBuild(DeliciousOnTerra.MODID, "textures/gui/single_roll.png");
    public static final ResourceLocation DICE_TEXTURE = ResourceLocation.tryBuild(DeliciousOnTerra.MODID, "textures/gui/d20.png");
    public static final int DICE_TEXTURE_SIZE = 256;
    private int currentTextureIndexY = 0;
    private int currentTextureIndexX = 0;
    private long lastRenderTime = 0;
    private static final long SWITCH_TIME_MS = 25; // 0.1秒
    private boolean done = false;

    private int result = 0;

    public RollDiceScreen(AddonDiceSystem addonDiceSystem) {
        super(Component.literal(addonDiceSystem.title));
        this.system = addonDiceSystem;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(
                Button.builder(Component.literal("roll"), p_280832_ -> {
                    this.done = false;
                    this.system.isStart = false;
                    this.system.startRoll();
                    this.result = system.getRolled();
                }).bounds(this.width / 2 - 100, this.height / 4 + 32, 20, 20).build());
    }

    protected void renderBg(@NotNull GuiGraphics graphics, float delta, int mouseX, int mouseY) {
        long currentTime = System.currentTimeMillis();

        int textureX = getTextureX(currentTextureIndexX);
        int textureY = getTextureY(currentTextureIndexY);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int screenX = width / 2 + 256 * 3;
        int screenY = height / 2 + 256;
        graphics.pose().pushPose();
        graphics.pose().scale(0.25f, 0.25f, 0.25f);
        if (!done && system.isStart) graphics.blit(TEXTURE, screenX, screenY, 256, 256, textureX, textureY, 256, 256, 2048, 2048);
        if (done && system.isStart) {
            int tX = this.getDiceTextureXByNum(system.getRolled());
            int tY = this.getDiceTextureYByNum(system.getRolled());
            graphics.blit(DICE_TEXTURE, screenX, screenY, 256, 256, tX, tY, 256, 256, 1280, 1024);
        }

        graphics.pose().popPose();
        if (currentTime - lastRenderTime >= SWITCH_TIME_MS && !done) {
            lastRenderTime = currentTime;

            if (currentTextureIndexX >= 8) {
                currentTextureIndexX = 0;
                currentTextureIndexY++;
            }
            if (currentTextureIndexY >= 8) {
                currentTextureIndexY = 0;
            }

            if (currentTextureIndexY == 7) {
                if (currentTextureIndexX < 3) {
                    currentTextureIndexX++;
                } else {
                    done = true;
                    currentTextureIndexY = 0;
                    currentTextureIndexX = 0;
                }
            } else if (!done) {
                currentTextureIndexX++;
            }

        }
        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
    }

    private int getTextureX(int index) {
        return index * DICE_TEXTURE_SIZE;
    }

    private int getTextureY(int index) {
        return index * DICE_TEXTURE_SIZE;
    }

    private int getDiceTextureYByNum(int i) {
        return switch (i) {
            case 1, 2, 3, 4, 5 -> 0;
            case 6, 7, 8, 9, 10 -> 256;
            case 11, 12, 13, 14, 15 -> 512;
            case 16, 17, 18, 19, 20 -> 768;
            default -> -1;
        };
    }

    private int getDiceTextureXByNum(int i) {
        return switch (i) {
            case 1, 6, 11, 16 -> 0;
            case 2, 7, 12, 17 -> 256;
            case 3, 8, 13, 18 -> 512;
            case 4, 9, 14, 19 -> 768;
            case 5, 10, 15, 20 -> 1024;
            default -> -1;
        };
    }

    @Override
    public void render(@NotNull GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.renderBg(context, delta, mouseX, mouseY);
    }
}
