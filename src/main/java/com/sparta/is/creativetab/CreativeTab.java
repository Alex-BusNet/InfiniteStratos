package com.sparta.is.creativetab;

import com.sparta.is.item.base.ItemTabLabel;
import com.sparta.is.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTab
{
    public static final CreativeTabs IS_TAB = new CreativeTabs(Reference.MOD_ID)
    {

        //TODO: Reimplement Yukihira as IS tab icon
        @Override
        public Item getTabIconItem()
        {
            return ItemTabLabel;
        }
    };
}
