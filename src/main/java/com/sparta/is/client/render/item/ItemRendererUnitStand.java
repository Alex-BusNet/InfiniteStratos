package com.sparta.is.client.render.item;

import com.sparta.is.client.render.model.ModelUnitStand;

public class ItemRendererUnitStand
{
    private ModelUnitStand modelUnitStand;

    public ItemRendererUnitStand()
    {
        modelUnitStand = new ModelUnitStand();
    }

//    @Override
//    public boolean handleRenderType(ItemStack item, ItemRenderType type)
//    {
//        return true;
//    }
//
//    @Override
//    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper)
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
//                renderUnitStand(1.0F, 1.0F, 1.0F);
//                return;
//            }
//            case EQUIPPED:
//            {
//                renderUnitStand(1.0F, 1.0F, 1.0F);
//                return;
//            }
//            case EQUIPPED_FIRST_PERSON:
//            {
//                renderUnitStand(1.5F, 1.5F, 1.5F);
//                return;
//            }
//            case INVENTORY:
//            {
//                renderUnitStand(0.0F, 0.0F, 0.0F);
//                return;
//            }
//            default:
//            {
//            }
//        }
//    }
//
//    public void renderUnitStand(float x, float y, float z)
//    {
//        GL11.glPushMatrix();
//
//        //Scale Translate, Rotate
//        GL11.glScalef(0.1f,0.1f,0.1f);
//        GL11.glTranslatef(x, y, z);
//
//        //Bind Texture
//        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Items.UNIT_STAND);
//
//        //Render
//        modelUnitStand.render();
//
//        GL11.glPopMatrix();
//    }
}
