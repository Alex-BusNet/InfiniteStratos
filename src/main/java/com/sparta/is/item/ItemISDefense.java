package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Names;

public class ItemISDefense extends ItemIS
{
    public ItemISDefense()
    {
        super();
        this.setUnlocalizedName(Names.Parts.ABSOLUTE_DEFENSE);
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.setMaxStackSize(1);
    }

}
