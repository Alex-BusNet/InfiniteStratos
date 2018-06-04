package com.sparta.is.client.render.Items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import java.util.List;

public class PerspectiveModel implements IBakedModel
{
    private final IBakedModel model2d, model3d;
    private final ItemCameraTransforms cameraTransforms;
    private final ItemOverrideList overrideList;
    private final IBakedModel baseModel, oneOffModel;

    public PerspectiveModel(IBakedModel model3d, IBakedModel model2d, IBakedModel baseModel)
    {
//        super();
        this.model3d = model3d;
        this.model2d = model2d;
        this.cameraTransforms = ItemCameraTransforms.DEFAULT;
        this.overrideList = ItemOverrideList.NONE;
        this.baseModel = baseModel;
        this.oneOffModel = null;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType transform)
    {
        ModelManager mm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager();
        IBakedModel mrl = (transform == ItemCameraTransforms.TransformType.GUI) ? model2d : model3d;

        return Pair.of(mrl, null);
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        return baseModel.getQuads(state, side, rand);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return baseModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return baseModel.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return baseModel.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return overrideList;
    }

}
