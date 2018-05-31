package com.sparta.is.block;

import com.sparta.is.core.block.BlockIS;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ISItemBlock extends ItemBlock
{
    private final BlockIS blockType;

    public ISItemBlock(final Block id)
    {
        super(id);
        this.blockType = (BlockIS)id;
        this.hasSubtypes = this.blockType.hasSubtypes();
    }

    @Override
    public int getMetadata(final int dmg)
    {
        return 0;
    }
}
