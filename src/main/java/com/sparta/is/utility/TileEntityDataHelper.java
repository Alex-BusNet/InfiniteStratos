package com.sparta.is.utility;

import com.sparta.is.tileentity.TileEntityIS;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class TileEntityDataHelper
{
    private static TileEntityDataHelper tileEntityDataHelper = null;

    private TileEntityDataHelper()
    {

    }

    public static TileEntityDataHelper getInstance()
    {
        if (tileEntityDataHelper == null)
        {
            tileEntityDataHelper = new TileEntityDataHelper();
        }

        return tileEntityDataHelper;
    }

    public Class getTileEntityClass(World world, BlockPos blockPos)
    {
        if (!world.isRemote)
        {
            return world.getTileEntity(blockPos).getClass();
        }

        return null;
    }

    public EnumFacing getOrientation(World world, BlockPos blockPos)
    {
        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityIS)
        {
            return ((TileEntityIS) world.getTileEntity(blockPos)).getOrientation();
        }

        return null;
    }

    public short getState(World world, BlockPos blockPos)
    {
        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityIS)
        {
            return ((TileEntityIS) world.getTileEntity(blockPos)).getState();
        }

        return Short.MIN_VALUE;
    }

    public String getCustomName(World world, BlockPos blockPos)
    {
        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityIS)
        {
            return ((TileEntityIS) world.getTileEntity(blockPos)).getCustomName();
        }

        return null;
    }

    public UUID getOwnerUUID(World world, BlockPos blockPos)
    {
        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityIS)
        {
            return ((TileEntityIS) world.getTileEntity(blockPos)).getOwnerUUID();
        }

        return null;
    }

    public String getOwnerName(World world, BlockPos blockPos)
    {
        if (!world.isRemote && world.getTileEntity(blockPos) instanceof TileEntityIS)
        {
            return ((TileEntityIS) world.getTileEntity(blockPos)).getOwnerName();
        }

        return null;
    }
}
