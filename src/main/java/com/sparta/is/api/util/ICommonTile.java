package com.sparta.is.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public interface ICommonTile
{
    void getDrops( World world, int x, int y, int z, List<ItemStack> drops );
}
