package com.sparta.is.block;

import com.sparta.is.creativetab.CreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockIS extends Block
{
    public BlockIS()
    {
        super(Material.WOOD);
        setCreativeTab(CreativeTab.IS_TAB);
    }
}
