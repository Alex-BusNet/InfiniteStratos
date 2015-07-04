package com.sparta.is.utility;

import com.sparta.is.tileentity.TileEntityIS;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.world.World;

public interface ITileEntityISProvider extends ITileEntityProvider
{
    TileEntityIS createNewArmor(World world, int slot);
}
