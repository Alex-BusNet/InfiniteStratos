package com.sparta.is.core.client.model;

import com.google.common.collect.Maps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class BakedISUnitModel implements IBakedModel
{
    // < State, < armament, baked quads list > > >
    private Map<String, Map<String, List<BakedQuad>>> bakedQuads = Maps.newHashMap();
    private Map<EnumFacing, List<BakedQuad>> faceQuads;
    private TextureAtlasSprite texture;

    public BakedISUnitModel() {}

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
    {
        return bakedQuads.get("full_deploy").get("chest");
    }

    public void addQuads(String state, String armamentName, List<BakedQuad> quads)
    {
        if(!bakedQuads.containsKey(state))
            bakedQuads.put(state, Maps.newHashMap());

        bakedQuads.get(state).put(armamentName, quads);
    }

    public void setTexture(TextureAtlasSprite newTexture)
    {
        texture = newTexture;
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
        return this.texture;
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return ItemOverrideList.NONE;
    }
}
