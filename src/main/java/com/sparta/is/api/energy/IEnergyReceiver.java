package com.sparta.is.api.energy;

import net.minecraft.util.EnumFacing;

public interface IEnergyReceiver extends IEnergyHandler
{
    int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate);
}
