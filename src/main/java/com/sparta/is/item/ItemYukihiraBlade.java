package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Names;

public class ItemYukihiraBlade extends ItemISWeaponBase
{
    public ItemYukihiraBlade()
    {
        super();
        this.setUnlocalizedName(Names.Parts.YUKIHIRA_BLADE);
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.maxStackSize=1;
    }
}
