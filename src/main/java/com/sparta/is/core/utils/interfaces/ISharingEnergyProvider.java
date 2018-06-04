package com.sparta.is.core.utils.interfaces;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface ISharingEnergyProvider
{
    int getEnergyToSplitShare();
    boolean doesShareEnergy();
    EnumFacing[] getEnergyShareSides();
    boolean canShareTo(TileEntity tile);
}
