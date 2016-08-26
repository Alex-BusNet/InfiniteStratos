package com.sparta.is.client.util;


import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class RenderUtils
{

    private static int pulse = 0;
    private static boolean doInc = true;

    public static void bindTexture(ResourceLocation texture)
    {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    public static int getCenteredTextOffest(FontRenderer fontRenderer, String string, int width)
    {
        return (width - fontRenderer.getStringWidth(string) / 2);
    }

    public static double getPulseValue()
    {
        return (Math.sin(System.nanoTime() / 100f) + 1) / 2;
    }


}
