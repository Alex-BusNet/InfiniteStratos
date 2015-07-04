package com.sparta.is.client.render.item;

import com.sparta.is.client.render.model.ModelElucidator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererElucidator implements IItemRenderer
{
    private final ModelElucidator modelElucidator;

    public ItemRendererElucidator()
    {
        modelElucidator = new ModelElucidator();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
    {
        switch (itemRenderType)
        {
            case ENTITY:
            {
                GL11.glPushMatrix();

                GL11.glScalef(0.7F, 0.7F, 0.7F);
                GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F); //X
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F); //Y
                GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F); //Z
                GL11.glTranslatef(0.5F, 0.0F, -1.3F); //Translate to player hand

                modelElucidator.render();
                GL11.glPopMatrix();

                return;
            }
            case EQUIPPED:
            {
                GL11.glPushMatrix();

                GL11.glScalef(0.7F, 0.7F, 0.7F);
                GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F); //X
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F); //Y
                GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F); //Z
                GL11.glTranslatef(0.5F, 0.0F, -1.3F); //Translate to player hand

                modelElucidator.render();
                GL11.glPopMatrix();

                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                GL11.glPushMatrix();

                GL11.glScalef(0.3F, 0.3F, 0.3F);
                GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F); //X
                GL11.glRotatef(0F, 0.0F, 1.0F, 0.0F); //Y
                GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F); //Z
                GL11.glTranslatef(0.5F, 0.0F, -1.3F); //Translate to player hand

                modelElucidator.render();
                GL11.glPopMatrix();

                return;
            }
            case INVENTORY:
            {
                GL11.glPushMatrix();

                GL11.glScalef(0.3F, 0.3F, 0.3F);
                GL11.glRotatef(45F, 1.0F, 0.0F, 0.0F); //X
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F); //Y
                GL11.glRotatef(30F, 0.0F, 0.0F, 1.0F); //Z
                GL11.glTranslatef(0.5F, 0.0F, -1.3F); //Translate to player hand

                modelElucidator.render();
                GL11.glPopMatrix();

                return;
            }
            default:
            {
            }
        }
    }
}
