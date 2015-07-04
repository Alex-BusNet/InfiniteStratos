package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Textures;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemISSwordMaterial extends Item
{
    public ItemISSwordMaterial()
    {
        super();
        this.setUnlocalizedName(Names.Materials.IS_SWORD);
        this.setTextureName(Textures.Materials.IS_SWORD.toString());
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

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
