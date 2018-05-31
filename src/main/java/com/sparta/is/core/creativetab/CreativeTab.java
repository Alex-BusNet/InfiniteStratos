package com.sparta.is.core.creativetab;

import com.sparta.is.core.reference.Reference;
import com.sparta.is.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs
{
    public static final CreativeTab IS_TAB = new CreativeTab();

    private CreativeTab()
    {
        super(Reference.MOD_ID);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem()
    {
        return this.getIconItemStack();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        return new ItemStack(ModItems.tabLabelItem);
    }

//    @Override
//    public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_)
//    {
//        for ( ItemIS item : ModItems.getItems() )
//        {
//            item.getSubItems(IS_TAB, p_78018_1_);
//        }
//
//        for ( ItemISMelee item : ModItems.getMeleeItems() )
//        {
//            item.getSubItems(IS_TAB, p_78018_1_);
//        }
//
//        for ( ItemISRange item : ModItems.getRangeItems() )
//        {
//            item.getSubItems(IS_TAB, p_78018_1_);
//        }
//
//        for ( ArmorIS item : ModItems.getUnits() )
//        {
//            item.getSubItems(IS_TAB, p_78018_1_);
//        }
//
//        for ( BlockIS blockIS : ModBlocks.getBlocks() )
//        {
//            blockIS.getSubBlocks(IS_TAB, p_78018_1_);
//        }
//    }
}
