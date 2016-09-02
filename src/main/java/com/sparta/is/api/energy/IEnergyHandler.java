package com.sparta.is.api.energy;

import net.minecraft.util.EnumFacing;

public interface IEnergyHandler extends IEnergyConnection
{
    int getEnergyStored(EnumFacing from);

    int getMaxEnergyStored(EnumFacing from);
}
