package com.sparta.is.utils;

import com.sparta.is.block.BlockEnum;
import com.sparta.is.item.base.ItemBlockEnum;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class BlockUtils
{
    private BlockUtils() {}

    public static ItemBlock getItemBlockFor(Block block)
    {
        return block instanceof BlockEnum ? new ItemBlockEnum((BlockEnum) block) : new ItemBlock(block);
    }
}
