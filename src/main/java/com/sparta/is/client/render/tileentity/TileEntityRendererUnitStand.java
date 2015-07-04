package com.sparta.is.client.render.tileentity;

import com.sparta.is.client.render.model.ModelUnitStand;
import com.sparta.is.reference.Textures;
import com.sparta.is.tileentity.TileEntityUnitStand;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererUnitStand extends TileEntitySpecialRenderer
{
    private final ModelUnitStand modelUnitStand = new ModelUnitStand();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if(tileEntity instanceof TileEntityUnitStand)
        {
            TileEntityUnitStand tileEntityUnitStand = (TileEntityUnitStand) tileEntity;
            ForgeDirection direction = tileEntityUnitStand.getOrientation();

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

    private void scaleTranslateRotate(double x, double y, double z, ForgeDirection orientation)
    {
        if (orientation == ForgeDirection.NORTH)
        {
            GL11.glTranslated(x + 0.4d, y, z + 0.5d);//y + 0.42d
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == ForgeDirection.EAST)
        {
            GL11.glTranslated(x + 0.5d, y, z + 0.4d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == ForgeDirection.SOUTH)
        {
            GL11.glTranslated(x + 0.6d, y, z + 0.5d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == ForgeDirection.WEST)
        {
            GL11.glTranslated(x + 0.5d, y, z + 0.6d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);
        }
    }

}
