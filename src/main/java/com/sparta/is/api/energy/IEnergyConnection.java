package com.sparta.is.api.energy;

import net.minecraft.util.EnumFacing;

public interface IEnergyConnection
{
    boolean canConnectEnergy(EnumFacing from);
}
