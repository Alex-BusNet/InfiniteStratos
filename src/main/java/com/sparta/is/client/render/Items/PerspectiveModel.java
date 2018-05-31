package com.sparta.is.client.render.Items;

import com.sparta.is.core.utils.interfaces.IModelRenderVariant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import java.util.List;

public class PerspectiveModel implements IModelRenderVariant
{
    private final ModelResourceLocation model2d, model3d;
    private final ItemCameraTransforms cameraTransforms;
    private final ItemOverrideList overrideList;
    private final IBakedModel baseModel, oneOffModel;

    public PerspectiveModel(ModelResourceLocation model3d, ModelResourceLocation model2d, IBakedModel baseModel, IBakedModel oneOffModel)
    {
        this.model2d = model2d;
        this.model3d = model3d;
        this.cameraTransforms = ItemCameraTransforms.DEFAULT;
        this.overrideList = ItemOverrideList.NONE;
        this.baseModel = baseModel;
        this.oneOffModel = oneOffModel;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType transform)
    {
        ModelManager mm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager();
        ModelResourceLocation mrl = (transform == ItemCameraTransforms.TransformType.GUI) ? model2d : model3d;
        Matrix4f matrix4f = null;

//        if(mrl.equals(model3d))
//        {
//            matrix4f = new Matrix4f();
//        }

        return Pair.of(mm.getModel(mrl), matrix4f);
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
