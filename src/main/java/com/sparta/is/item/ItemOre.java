package com.sparta.is.item;

import cofh.core.util.helpers.ItemHelper;
import com.sparta.is.block.BlockOre;
import com.sparta.is.core.block.ItemBlockIS;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemOre extends ItemBlockIS
{
    public ItemOre(Block block)
    {
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return "tile.is:" + BlockOre.OreVariant.byMetadata(ItemHelper.getItemDamage(itemStack)).getName() + ".name";
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return BlockOre.OreVariant.byMetadata(ItemHelper.getItemDamage(stack)).getRarity();
    }
}
