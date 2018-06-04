package com.sparta.is.client.render.tile;

import com.sparta.is.client.utils.RenderUtils;
import com.sparta.is.core.armor.ArmorIS;
import com.sparta.is.core.utils.ItemStackUtils;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.tileentity.TileEntityISUnitStation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderUnitStation extends FastTESR<TileEntityISUnitStation>
{
    @Override
    public void renderTileEntityFast(TileEntityISUnitStation tile, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer)
    {
        if(tile == null)
        {
            return;
        }

        ItemStack stack = tile.slots.getStackInSlot(0);
        if( ItemStackUtils.isValid(stack))
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x+0.5F, (float)y+1F, (float)z+0.5F);

            double boop = Minecraft.getSystemTime()/800D;
            GlStateManager.translate(0D, Math.sin(boop%(2*Math.PI))*0.065, 0D);

            if(stack.getItem() instanceof ArmorIS )
            {
                GlStateManager.rotate(90.0F, 0, 1, 0);
                GlStateManager.rotate(225.0f, 1, 0, 0);
            }

            float scale = stack.getItem() instanceof ItemBlock ? 0.85F : 0.65F;
            GlStateManager.scale(scale, scale, scale);
            try
            {
                RenderUtils.renderItemInWorld(stack);
            }
            catch(Exception e)
            {
                LogHelper.error("Something went wrong trying to render an item in a display stand! The item is " + stack.getItem().getRegistryName() + "!", e);
            }

            GlStateManager.popMatrix();
        }
    }
}
