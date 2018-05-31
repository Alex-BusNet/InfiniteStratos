package com.sparta.is.block;

import com.sparta.is.core.block.BlockIS;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.LogHelper;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnum extends BlockIS
{
    private final IEnumMeta[] VARIANTS;
    private int activeVariant = 0;

    public BlockEnum(String name, IEnumMeta[] variants)
    {
        this(name, Material.ROCK, variants);
    }

    public BlockEnum(String name, Material material, IEnumMeta[] variants)
    {
        super(name, material);
        if(variants.length > 0)
        {
            VARIANTS = variants;
        }
        else
        {
            VARIANTS = new IEnumMeta[0];
        }
    }

    public String getUnlocalizedName(ItemStack itemStack)
    {
        if(itemStack != null && itemStack.getItem() != null && Block.getBlockFromItem(itemStack.getItem()) instanceof BlockIS)
        {
            if(VARIANTS.length > 0)
            {
                return String.format("tile.%s:%s", Reference.MOD_ID, VARIANTS[Math.abs(itemStack.getMetadata() % VARIANTS.length)]);
            }
        }

        return super.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addModelVariants(TIntObjectHashMap<ModelResourceLocation> variants)
    {
        LogHelper.info("\t\tLoading variants for " + this.getRegistryName());
        for(IEnumMeta em : VARIANTS)
        {
            LogHelper.info("\t\t\tVariant name: " + em.getName());
            addVariant(variants, activeVariant++, em.getName());
        }

        activeVariant = 0;
    }

}
