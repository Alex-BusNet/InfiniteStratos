package com.sparta.is.armor.units;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

import javax.annotation.Nullable;

public class Old_Byakushiki extends Render
{
    private static Minecraft mc = FMLClientHandler.instance().getClient();

    public Old_Byakushiki()
    {
        super(new RenderManager(mc.getTextureManager(), mc.getRenderItem()));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }
}
