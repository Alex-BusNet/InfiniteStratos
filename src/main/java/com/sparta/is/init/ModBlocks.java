package com.sparta.is.init;

import com.sparta.is.block.BlockFlag;
import com.sparta.is.block.BlockIS;
import com.sparta.is.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockIS flag = new BlockFlag();

    public static void init()
    {
        GameRegistry.registerBlock(flag, "Flag");
    }
}
