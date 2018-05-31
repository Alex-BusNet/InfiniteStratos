package com.sparta.is.client.render.entity;

import com.sparta.is.client.render.model.ModelTabane;
import com.sparta.is.entity.EntityTabane;
import com.sparta.is.core.reference.Textures;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityRendererTabane extends RenderLiving<EntityTabane>
{

    public EntityRendererTabane(RenderManager renderManager)
    {
        super(renderManager, new ModelTabane(), 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTabane entity)
    {
        return Textures.Villagers.TABANE;
    }
}
