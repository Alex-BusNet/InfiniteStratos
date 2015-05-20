package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Names;

public class ItemISProcessor extends ItemIS
{
    public ItemISProcessor()
    {
        super();
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.setUnlocalizedName(Names.Parts.IS_PROCESSOR);
    }
}
