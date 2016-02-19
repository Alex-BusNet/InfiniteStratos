package com.sparta.is.client.render.entity;

import com.sparta.is.client.render.model.ModelTabane;
import com.sparta.is.reference.Textures;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class EntityRendererTabane extends RenderLiving
{
    private static ModelTabane modelTabane = new ModelTabane();

    public EntityRendererTabane()
    {
        super(modelTabane, 1.0F);
        this.setRenderPassModel(modelTabane);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return Textures.Villagers.TABANE;
    }


}
