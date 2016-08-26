package com.sparta.is.creativetab;

import com.sparta.is.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabIS
{
    public static final CreativeTabs IS_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            //TODO: Change this to an IS item
            return Items.ELYTRA;
        }
    };
}
