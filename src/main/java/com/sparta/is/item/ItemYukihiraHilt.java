package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Names;

public class ItemYukihiraHilt extends ItemISWeaponBase
{
    public ItemYukihiraHilt()
    {
        super();
        this.setUnlocalizedName(Names.Parts.YUKIHIRA_HILT);
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.maxStackSize=1;
    }
}
