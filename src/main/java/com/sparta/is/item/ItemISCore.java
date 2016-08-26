package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemISCore extends ItemIS
{
    public ItemISCore()
    {
        //TODO: Add Core Variants to super();
        super(Names.Parts.IS_CORE);
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.setUnlocalizedName(Names.Parts.IS_CORE);
        this.setMaxStackSize(1);

    }


    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
    {
        list.add("The heart of every IS Unit.");
        list.add("Only 467 of them were ever made.");
    }

}
