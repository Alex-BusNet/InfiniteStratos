package com.sparta.is.client.render.tileentity;

import com.sparta.is.client.render.model.ModelISUnitStation;
import com.sparta.is.reference.Textures;
import com.sparta.is.tileentity.TileEntityISStation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererISUnitStation extends TileEntitySpecialRenderer
{
    private final ModelISUnitStation modelISUnitStation = new ModelISUnitStation();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick, int stage)
    {
        if(tileEntity instanceof TileEntityISStation)
        {
            TileEntityISStation tileEntityISStation = (TileEntityISStation) tileEntity;
            EnumFacing direction = tileEntityISStation.getOrientation();

            GL11.glPushMatrix();

            //Scale, Translate, Rotate
            scaleTranslateRotate(x, y, z, direction);

            //Bind Texture
            this.bindTexture(Textures.Items.IS_UNIT_STATION);

            //Render
            modelISUnitStation.render();

            GL11.glPopMatrix();
        }
    }

    private void scaleTranslateRotate(double x, double y, double z, EnumFacing orientation)
    {
        if (orientation == EnumFacing.NORTH)
        {
            GL11.glTranslated(x + 0.4d, y + 0.42d, z + 0.5d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == EnumFacing.EAST)
        {
            GL11.glTranslated(x + 0.5d, y + 0.42d, z + 0.4d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == EnumFacing.SOUTH)
        {
            GL11.glTranslated(x + 0.6d, y + 0.42d, z + 0.5d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == EnumFacing.WEST)
        {
            GL11.glTranslated(x + 0.5d, y + 0.42d, z + 0.6d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);
        }
    }


}
