package com.sparta.is.item.base;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Textures;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemISAdamantine extends Item
{
    public ItemISAdamantine()
    {
        super();
        setUnlocalizedName(Names.Materials.IS_ARMOR);
        setCreativeTab(CreativeTab.IS_TAB);
        setMaxStackSize(64);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("material.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("material.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
