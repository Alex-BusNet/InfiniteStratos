package com.sparta.is.utils;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTTaggable
{
    void readFromNBT(NBTTagCompound nbtTagCompound);

    void writeToNBT(NBTTagCompound nbtTagCompound);

    String getTagLabel();
}
