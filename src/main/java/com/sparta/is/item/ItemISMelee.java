package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.reference.Materials;
import com.sparta.is.reference.Textures;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemISMelee extends ItemSword
{
    public ItemISMelee()
    {
        super(Materials.Weapons.IS_SWORD);
        setCreativeTab(CreativeTab.IS_TAB);
        setMaxStackSize(1);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
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
