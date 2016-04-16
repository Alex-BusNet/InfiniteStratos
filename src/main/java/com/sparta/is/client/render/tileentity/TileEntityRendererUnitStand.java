package com.sparta.is.client.render.tileentity;

import com.sparta.is.client.render.model.ModelUnitStand;
import com.sparta.is.reference.Textures;
import com.sparta.is.tileentity.TileEntityUnitStand;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererUnitStand extends TileEntitySpecialRenderer
{
    private final ModelUnitStand modelUnitStand = new ModelUnitStand();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick, int stage)
    {
        if(tileEntity instanceof TileEntityUnitStand)
        {
            TileEntityUnitStand tileEntityUnitStand = (TileEntityUnitStand) tileEntity;
            EnumFacing direction = tileEntityUnitStand.getOrientation();

            GL11.glPushMatrix();

            //Scale, Translate, Rotate
            scaleTranslateRotate(x, y, z, direction);

            //Bind Texture
            this.bindTexture(Textures.Items.UNIT_STAND);

            //Render
            modelUnitStand.render();

            GL11.glPopMatrix();
        }
    }

    private void scaleTranslateRotate(double x, double y, double z, EnumFacing orientation)
    {
        if (orientation == EnumFacing.NORTH)
        {
            GL11.glTranslated(x + 0.4d, y, z + 0.5d);//y + 0.42d
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == EnumFacing.EAST)
        {
            GL11.glTranslated(x + 0.5d, y, z + 0.4d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == EnumFacing.SOUTH)
        {
            GL11.glTranslated(x + 0.6d, y, z + 0.5d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == EnumFacing.WEST)
        {
            GL11.glTranslated(x + 0.5d, y, z + 0.6d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);
        }
    }

}
