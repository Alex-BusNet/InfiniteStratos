package com.sparta.is.item;

import com.sparta.is.item.base.ItemIS;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Textures;
import net.minecraft.item.ItemStack;

public class ItemAdamantine extends ItemIS
{
    public ItemAdamantine()
    {
        super(Names.Materials.IS_ARMOR);
        setMaxStackSize(64);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("material.%s%s", Textures.RESOURCE_PREFIX, Names.Materials.IS_ARMOR);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("material.%s%s", Textures.RESOURCE_PREFIX, Names.Materials.IS_ARMOR);
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
