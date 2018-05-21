package com.sparta.is.creativetab;

import com.sparta.is.init.ModItems;
import com.sparta.is.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs
{
    public CreativeTab()
    {
        super(Reference.MOD_ID);
    }

    public static final CreativeTab IS_TAB = new CreativeTab();

    //TODO: Reimplement ItemTabLabel as IS tab icon
    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.tabLabelItem);
    }
}
