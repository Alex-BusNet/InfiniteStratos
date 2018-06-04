package com.sparta.is.item;

import com.sparta.is.core.block.ItemBlockIS;
import com.sparta.is.core.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemDisplayStand extends ItemBlockIS
{
    public ItemDisplayStand(Block block)
    {
        super(block);
        setHasSubtypes(false);
        setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return "tile.is:" + Names.Blocks.ITEM_DISPLAY_STAND + ".name";
    }
}
