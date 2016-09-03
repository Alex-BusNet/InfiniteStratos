package com.sparta.is.init;

import com.sparta.is.block.BlockAdamantineOre;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    public static Block adamantine;

    public static void init()
    {
        adamantine = new BlockAdamantineOre();

        OreDictionary.registerOre("adamantineOre", new ItemStack(adamantine, 1, 1));
    }

    private static void registerTileEntity(Block block, String name)
    {

    }

    private static void registerTile()
    {

    }
}
