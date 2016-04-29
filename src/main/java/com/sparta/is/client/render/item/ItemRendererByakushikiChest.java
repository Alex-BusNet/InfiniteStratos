package com.sparta.is.client.render.item;

import com.sparta.is.client.render.model.ModelByakushikiChest;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ISmartItemModel;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class ItemRendererByakushikiChest implements ISmartItemModel
{
    private final ModelByakushikiChest modelByakushikiChest;

    public ItemRendererByakushikiChest()
    {
        modelByakushikiChest = new ModelByakushikiChest();
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

                GL11.glScalef(0.5F,0.5F,0.5F);
                modelByakushikiChest.render();

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
                GL11.glTranslatef(0.5F, 0.0F, - 1.3F); //Translate to player hand

                modelByakushikiChest.render();
                GL11.glPopMatrix();
                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                GL11.glPushMatrix();

                GL11.glScalef(0.5F,0.5F,0.5F);
                modelByakushikiChest.render();

                GL11.glPopMatrix();
                return;
            }
            case INVENTORY:
            {
                GL11.glPushMatrix();

                GL11.glScalef(0.5F,0.5F,0.5F);
                modelByakushikiChest.render();

                GL11.glPopMatrix();
                return;
            }
            default:
            {
            }
        }
    }

    @Override
    public IBakedModel handleItemState(ItemStack stack)
    {
        return null;
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_)
    {
        return null;
    }

    @Override
    public List<BakedQuad> getGeneralQuads()
    {
        return null;
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return false;
    }

    @Override
    public boolean isGui3d()
    {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return null;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms()
    {
        return null;
    }
}
