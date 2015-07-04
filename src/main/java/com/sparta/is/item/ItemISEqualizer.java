package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import java.util.List;

public class ItemISEqualizer extends ItemSword
{
    public ItemISEqualizer()
    {
        super(Material.Weapons.IS_SWORD);
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
    {

    }


}
