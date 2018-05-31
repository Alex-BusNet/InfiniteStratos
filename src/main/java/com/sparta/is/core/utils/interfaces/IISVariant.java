package com.sparta.is.core.utils.interfaces;

import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IISVariant
{
    @SideOnly(Side.CLIENT)
    void addModelVariants(TIntObjectHashMap<ModelResourceLocation> variants);

    @SideOnly(Side.CLIENT)
    void addVariant(TIntObjectHashMap<ModelResourceLocation> variants, int meta, String suffix);

    @SideOnly(Side.CLIENT)
    void registerVariants();
}
