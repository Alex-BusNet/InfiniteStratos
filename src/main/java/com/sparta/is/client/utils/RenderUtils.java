package com.sparta.is.client.utils;

import com.sparta.is.core.utils.ItemStackUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderUtils
{
    public static void bindTexture(ResourceLocation texture)
    {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    public static int getCenteredTextOffset(FontRenderer fontRenderer, String string, int width)
    {
        return (width - fontRenderer.getStringWidth(string)) / 2;
    }

    private static double getPulseValue()
    {
        return (Math.sin(System.nanoTime() / 100f) + 1) / 2;
    }

    @SideOnly(Side.CLIENT)
    public static void renderBlockInWorld(Block block, int meta)
    {
        renderItemInWorld(new ItemStack(block, 1, meta));
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemInWorld(ItemStack stack)
    {
        if( ItemStackUtils.isValid(stack))
        {
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
