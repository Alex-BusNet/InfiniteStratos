package com.sparta.is.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;

public abstract class BlockTileEntity extends BlockIS implements ITileEntityProvider
{
    public BlockTileEntity(String name)
    {
        this(name, Material.ROCK);
    }

    public BlockTileEntity(String name, Material material)
    {
        super(name, material);
    }

}
