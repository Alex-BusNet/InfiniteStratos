package com.sparta.is.core.utils.interfaces;

import com.sparta.is.core.tileentity.CustomEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IEnergyDisplay
{
    @SideOnly(Side.CLIENT)
    CustomEnergyStorage getEnergyStorage();

    @SideOnly(Side.CLIENT)
    boolean needsHoldShift();
}
