package com.sparta.is.item;

import com.sparta.is.item.base.ItemIS;

public class ItemOre extends ItemIS
{
    public ItemOre()
    {
        super("adamantine_ore");
        setMaxStackSize(64);
        setHasSubtypes(true);
    }
}
