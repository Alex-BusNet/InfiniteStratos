package com.sparta.is.core.utils.interfaces;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

public interface IModelRenderVariant extends IBakedModel
{
    @Override
    default List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
    {
        return null;
    }

    @Override
    default boolean isAmbientOcclusion()
    {
        return false;
    }

    @Override
    default boolean isGui3d()
    {
        return false;
    }

    @Override
    default boolean isBuiltInRenderer()
    {
        return false;
    }

    @Override
    default TextureAtlasSprite getParticleTexture()
    {
        return null;
    }

    @Override
    default ItemOverrideList getOverrides()
    {
        return ItemOverrideList.NONE;
    }

    @Override
    Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType);
//    {
//        Item item = (Item) this;
//        ModelManager mm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager();
//        ModelResourceLocation mrl;
//        String unlocalName = item.getUnlocalizedName().toLowerCase();
//        unlocalName = unlocalName.substring(unlocalName.lastIndexOf(':') + 1);
//
//        LogHelper.info(unlocalName + ": " + cameraTransformType.name());
//
//        if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
//            mrl = new ModelResourceLocation(Reference.MOD_ID + ":" + unlocalName + "_inv");
//        else
//            mrl = new ModelResourceLocation(Reference.MOD_ID + ":" + unlocalName);
//
//        LogHelper.info(mrl.toString());
//
//        return Pair.of(mm.getModel(mrl), null);
//    }
}
