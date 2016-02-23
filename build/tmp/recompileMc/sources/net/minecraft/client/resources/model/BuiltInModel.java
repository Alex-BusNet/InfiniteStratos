package net.minecraft.client.resources.model;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class BuiltInModel implements IBakedModel
{
    private ItemCameraTransforms cameraTransforms;

    public BuiltInModel(ItemCameraTransforms p_i46086_1_)
    {
        this.cameraTransforms = p_i46086_1_;
    }

    public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_)
    {
        return null;
    }

    public List<BakedQuad> getGeneralQuads()
    {
        return null;
    }

    public boolean isAmbientOcclusion()
    {
        return false;
    }

    public boolean isGui3d()
    {
        return true;
    }

    public boolean isBuiltInRenderer()
    {
        return true;
    }

    public TextureAtlasSprite getParticleTexture()
    {
        return null;
    }

    public ItemCameraTransforms getItemCameraTransforms()
    {
        return this.cameraTransforms;
    }
}