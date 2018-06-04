package com.sparta.is.core.utils.interfaces;

import net.minecraft.util.EnumFacing;

public interface ISharingFluidHandler
{
    int getMaxFluidAmountToSplitShare();

    boolean doesShareFluid();

    EnumFacing[] getFluidShareSides();
}
