package com.sparta.is.client.gui.overlay;

import com.sparta.is.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;

public class UnitOverlay extends Gui
{
    private Minecraft mc;

    public UnitOverlay(Minecraft mc)
    {
        super();

        this.mc = mc;
    }

    private final static int BAR_WIDTH = 81;
    private final static int BAR_HEIGHT = 9;
    private final static int BAR_SPACING_ABOVE_EXP_BAR = 3;

    public void renderStatusBar(int screenWidth, int screenHeight)
    {
        World world = mc.theWorld;
        EntityPlayer player = mc.thePlayer;

        FontRenderer fontRenderer = mc.fontRendererObj;

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


        mc.renderEngine.bindTexture(Textures.Gui.OVERLAY);

        final int vanillaExpLeftX = screenWidth / 2 - 91;
        final int vanillaExpLeftY = screenHeight - 32 + 3;

        GL11.glTranslatef(vanillaExpLeftX, vanillaExpLeftY - BAR_SPACING_ABOVE_EXP_BAR - BAR_HEIGHT, 0);

        drawTexturedModalRect(0, 0, 0, 0, BAR_WIDTH, BAR_HEIGHT);
        drawTexturedModalRect(0, 0, 0, BAR_HEIGHT, (int)(BAR_WIDTH*(player.getTotalArmorValue()/20f)), BAR_HEIGHT);

        GL11.glPushMatrix();
        GL11.glTranslatef(1, 1, 0);

        float maxHP = player.getMaxHealth();
        float absorbtionAmount = player.getAbsorptionAmount();
        float effectiveHp = player.getHealth() + absorbtionAmount;

        GL11.glPushMatrix();

        GL11.glScalef((BAR_WIDTH - 2) * Math.min(1, effectiveHp / maxHP), 1, 1);

        final int WITHER_EFFECT_ID = 20;
        final int POISON_EFFECT_ID = 19;
        final int REGEN_EFFECT_ID = 10;
        final int NORMAL_TEXTURE_U = BAR_WIDTH;     // red texels  - see mbe40_hud_overlay.png
        final int REGEN_TEXTURE_U = BAR_WIDTH + 1;  //  green texels
        final int POISON_TEXTURE_U = BAR_WIDTH + 2;  // black texels
        final int WITHER_TEXTURE_U = BAR_WIDTH + 3;  // brown texels

        if (player.isPotionActive(Potion.getPotionById(WITHER_EFFECT_ID))) {
            drawTexturedModalRect(0, 0, WITHER_TEXTURE_U, 0, 1, BAR_HEIGHT - 2);
        }
        else if (player.isPotionActive(Potion.getPotionById(POISON_EFFECT_ID))) {
            drawTexturedModalRect(0, 0, POISON_TEXTURE_U, 0, 1, BAR_HEIGHT - 2);
        }
        else if (player.isPotionActive(Potion.getPotionById(REGEN_EFFECT_ID))) {
            drawTexturedModalRect(0, 0, REGEN_TEXTURE_U, 0, 1, BAR_HEIGHT - 2);
        }
        else {
            drawTexturedModalRect(0, 0, NORMAL_TEXTURE_U, 0, 1, BAR_HEIGHT - 2);
        }

        GL11.glPopMatrix();

        GL11.glTranslatef(BAR_WIDTH - 3, 1, 0);

        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 1);

        String string = decimalFormat.format(effectiveHp) + "/" + decimalFormat.format(maxHP);

        if(absorbtionAmount > 0)
        {
            fontRenderer.drawString(string, -fontRenderer.getStringWidth(string) + 1, 2, 0x5A2B00);

            fontRenderer.drawString(string, -fontRenderer.getStringWidth(string), 1, 0xFFD200);
        }
        else
        {
            fontRenderer.drawString(string, -fontRenderer.getStringWidth(string) + 1, 2, 0x4D0000);
            fontRenderer.drawString(string, -fontRenderer.getStringWidth(string), 1, 0xFFFFFF);
        }

        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}
