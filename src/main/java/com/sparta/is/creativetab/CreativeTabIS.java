package com.sparta.is.creativetab;

import com.sparta.is.init.ModItems;
import com.sparta.is.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabIS
{
    public static final CreativeTabs IS_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.tabLabel;
        }
    };
}
