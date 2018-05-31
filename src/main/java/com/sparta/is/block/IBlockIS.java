package com.sparta.is.block;

import com.sparta.is.core.utils.helpers.ResourceLocationHelper;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockIS
{
    @SideOnly(Side.CLIENT)
    default void addModelVariants(TIntObjectHashMap<ModelResourceLocation> variants)
    {
        addVariant(variants, 0, "");
    }

    @SideOnly(Side.CLIENT)
    default void addVariant(TIntObjectHashMap<ModelResourceLocation> variants, int meta, String suffix)
    {
        variants.put(meta, ResourceLocationHelper.getModelResourceLocation(((Block)this).getRegistryName(), suffix));
    }

    @SideOnly(Side.CLIENT)
    default void registerVariants()
    {
        Block thisItem = (Block) this;
        TIntObjectHashMap<ModelResourceLocation> variants = new TIntObjectHashMap<>();
        addModelVariants(variants);
        for(int key : variants.keys())
        {
            ModelResourceLocation variant = variants.get(key);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(thisItem), key, variant);
        }
    }
}
