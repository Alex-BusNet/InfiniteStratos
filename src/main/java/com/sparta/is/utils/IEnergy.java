package com.sparta.is.utils;

import net.minecraft.item.ItemStack;

public interface IEnergy
{
    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

    int getStoredEnergy(ItemStack container);

    int getMaxEnergyStored(ItemStack container);
}
