package com.sparta.is.api.energy;

import net.minecraft.util.EnumFacing;

public interface IEnergyProvider extends IEnergyHandler
{
    int extractEnergy(EnumFacing from, int maxExtract, boolean simulate);
}
