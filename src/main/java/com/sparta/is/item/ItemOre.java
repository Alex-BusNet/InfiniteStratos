package com.sparta.is.item;

import com.sparta.is.item.base.ItemIS;

public class ItemOre extends ItemIS
{
    private static final String[] VARIANTS = {"isarmormaterial", "isswordmaterial"};
    public ItemOre()
    {
        super("oreitem", VARIANTS);
        setMaxStackSize(64);
        setHasSubtypes(true);
    }
}