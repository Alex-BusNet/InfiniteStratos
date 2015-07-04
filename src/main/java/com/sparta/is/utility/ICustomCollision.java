package com.sparta.is.utility;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public interface ICustomCollision
{
    Iterable<AxisAlignedBB> getSelectedBoundingBoxesFromPool( World w, int x, int y, int z, Entity thePlayer, boolean b );

    void addCollidingBlockToList( World w, int x, int y, int z, AxisAlignedBB bb, List<AxisAlignedBB> out, Entity e );
}
