package com.sparta.is.client.render.item;

import com.sparta.is.client.render.model.ModelISUnitStation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class ItemRendererISUnitStation extends TileEntitySpecialRenderer
{
    private final ModelISUnitStation modelISUnitStation;

    public ItemRendererISUnitStation()
    {
        modelISUnitStation = new ModelISUnitStation();
    }



//    @Override
//    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
//
//        return true;
//    }
//
//    @Override
//    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
//    {
//        return true;
//    }
//
//    @Override
//    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
//    {
//        switch (itemRenderType)
//        {
//            case ENTITY:
//            {
//                renderISUnitStation(0.0F, 0.0F, 0.0F);
//                return;
//            }
//            case EQUIPPED:
//            {
//                renderISUnitStation(1.0F, 1.0F, 1.0F);
//                return;
//            }
//            case EQUIPPED_FIRST_PERSON:
//            {
//                renderISUnitStation(1.5F, 1.5F, 1.5F);
//                return;
//            }
//            case INVENTORY:
//            {
//                renderISUnitStation(0.0F, 0.0F, 0.0F);
//                return;
//            }
//            default:
//            {
//            }
//        }
//    }
//
//    public void renderISUnitStation(float x, float y, float z)
//    {
//        GL11.glPushMatrix();
//
//        //Scale Translate, Rotate
//        GL11.glScalef(0.2f,0.2f,0.2f);
//        GL11.glTranslatef(x, y, z);
//
//        //Bind Texture
//        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Items.IS_UNIT_STATION);
//
//        //Render
//        modelISUnitStation.render();
//
//        GL11.glPopMatrix();
//    }
}
