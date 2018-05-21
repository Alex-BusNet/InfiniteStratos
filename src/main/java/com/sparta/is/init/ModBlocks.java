package com.sparta.is.init;

import com.sparta.is.block.BlockIS;
import com.sparta.is.block.BlockISUnitStation;
import com.sparta.is.block.BlockOre;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModBlocks
{
    private static final List<BlockIS> BLOCKS = new ArrayList<>();
//    public static final BlockIS adamantine = new BlockAdamantine();
    public static final BlockIS oreBlock = new BlockOre();
    public static final BlockIS unitStation = new BlockISUnitStation();

    private ModBlocks() {}

    public static void register(BlockIS block)
    {
        BLOCKS.add(block);
    }

    public static Collection<BlockIS> getBlocks()
    {
        return BLOCKS;
    }

    private static void registerTileEntity(Block block, String name)
    {

    }

    private static void registerTile()
    {

    }
}
