package com.sparta.is.item.base;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.reference.Materials;
import com.sparta.is.reference.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemISMelee extends ItemSword
{
    private String BASE_NAME;

    public ItemISMelee(String name)
    {
        super(Materials.Weapons.IS_SWORD);
        setRegistryName(name);
        setCreativeTab(CreativeTab.IS_TAB);
        setMaxStackSize(1);
        BASE_NAME = name;
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("weapon.%s:%s", Reference.MOD_ID, BASE_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("weapon.%s:%s", Reference.MOD_ID, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }




}
