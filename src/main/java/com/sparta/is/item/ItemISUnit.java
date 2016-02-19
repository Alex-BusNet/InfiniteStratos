package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Textures;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemISUnit extends Item
{

    public ItemISUnit()
    {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabIS.IS_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
