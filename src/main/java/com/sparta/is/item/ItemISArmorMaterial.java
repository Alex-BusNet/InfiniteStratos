package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Textures;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemISArmorMaterial extends Item
{
    public ItemISArmorMaterial()
    {
        super();
        this.setUnlocalizedName(Names.Materials.IS_ARMOR);
//        this.setTextureName(Textures.Materials.IS_ARMOR.toString());
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.setMaxStackSize(64);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("material.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("material.%s%s",Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
