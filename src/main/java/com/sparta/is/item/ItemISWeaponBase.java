package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemISWeaponBase extends Item
{
    public ItemISWeaponBase()
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setCreativeTab(CreativeTabIS.IS_TAB);
    }

    public String getUnlocalizedName (Item item)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName ()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
        //item.modid:itemname.name
    }

    @Override
    public String getUnlocalizedName (ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    protected float baseSpeed ()
    {
        return  1.5f;
    }

    protected float effectiveSpeed()
    {
        return 15f;
    }
}
