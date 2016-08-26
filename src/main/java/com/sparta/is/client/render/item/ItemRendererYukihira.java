package com.sparta.is.client.render.item;

import com.sparta.is.client.render.model.ItemModelYukihira;

public class ItemRendererYukihira
{
    ItemModelYukihira swordModel;

    public ItemRendererYukihira()
    {
        swordModel=new ItemModelYukihira();
    }

//    @Override
//    public boolean handleRenderType(ItemStack item, ItemRenderType type)
//    {
//        return true;
//    }
//
//    @Override
//    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
//    {
//        switch(type)
//        {
//            case INVENTORY:
//                return true;
//            default:
//                break;
//        }
//        return false;
//    }
//
//    @Override
//    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
//    {
//        switch(type)
//        {
//            case EQUIPPED:
//                GL11.glPushMatrix();
//                GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F); //X
//                GL11.glRotatef(-30F, 0.0F, 1.0F, 0.0F); //Y
//                GL11.glRotatef(-90F, 0.0F, 0.0F, 1.0F); //Z
//                GL11.glTranslatef(0.1F, -0.4F, -0.2F); //Translate to player hand
//                Minecraft.getMinecraft().renderEngine.bindTexture(Textures.Items.YUKIHIRA_MODEL_TEXTURE);
//                swordModel.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f);
//                GL11.glPopMatrix();
//                break;
//            case EQUIPPED_FIRST_PERSON:
//                GL11.glPushMatrix();
//                GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F); //X
//                GL11.glRotatef(-30F, 0.0F, 1.0F, 0.0F); //Y
//                GL11.glRotatef(-60F, 0.0F, 0.0F, 1.0F); //Z
//                GL11.glTranslatef(0.1F, -0.4F, -0.2F); //Translate to player hand
//                Minecraft.getMinecraft().renderEngine.bindTexture(Textures.Items.YUKIHIRA_MODEL_TEXTURE);
//                swordModel.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f);
//                GL11.glPopMatrix();
//                break;
//            case ENTITY:
//                GL11.glPushMatrix();
//                GL11.glScalef(0.5F, 1.0F, 1.0F);
//                GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F); //X
//                GL11.glRotatef(-30F, 0.0F, 1.0F, 0.0F); //Y
//                GL11.glRotatef(-90F, 0.0F, 0.0F, 1.0F); //Z
//                GL11.glTranslatef(0.1F, -0.4F, -0.2F); //Translate to player hand
//                Minecraft.getMinecraft().renderEngine.bindTexture(Textures.Items.YUKIHIRA_MODEL_TEXTURE);
//                swordModel.render((Entity) data[1], 0.0F, 0.0f, 0.0F, 0.0F, 0.0F, 0.1F);
//                GL11.glPopMatrix();
//                break;
//            case INVENTORY:
//                GL11.glPushMatrix();
//                GL11.glScalef(0.5F, 0.5F, 0.5F);
//                GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F); //X
//                GL11.glRotatef(-30F, 0.0F, 1.0F, 0.0F); //Y
//                GL11.glRotatef(-90F, 0.0F, 0.0F, 1.0F); //Z
//                GL11.glTranslatef(0.1F, -0.4F, -0.2F); //Translate to player hand
//                Minecraft.getMinecraft().renderEngine.bindTexture(Textures.Items.YUKIHIRA_MODEL_TEXTURE);
//                swordModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
//                GL11.glPopMatrix();
//                break;
//            default:
//                break;
//        }
//
//    }
}
