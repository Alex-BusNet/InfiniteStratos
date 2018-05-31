package com.sparta.is.client.render.units;

//import com.sparta.is.entity.driveables.types.UnitType;
import com.sparta.is.core.utils.helpers.ResourceLocationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class UnitRenderer extends Render
{
    private static final ItemRenderer renderer = new ItemRenderer(Minecraft.getMinecraft());
    private static RenderItem item;

    public UnitRenderer(RenderManager manager)
    {
        super(manager);
        item = Minecraft.getMinecraft().getRenderItem();
        shadowSize = 0.5f;
    }

//    public void render(EntityISUnit unit, double d, double d1, double d2, float f, float f1)
//    {
//        bindEntityTexture(unit);
//        float scale = 1f / 16f;
//        UnitType type = unit.getUnitType();
//        GL11.glPushMatrix();
//        GL11.glTranslatef((float)d, (float)d1, (float)d2);
//        float dYaw = unit.axes.getYaw() - unit.prevRotationYaw;
//    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return ResourceLocationHelper.getResourceLocation(entity.getName());
    }
}
