package com.sparta.is.utils;

import com.sparta.is.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class EntityHelper
{
    public static NBTTagCompound getCustomEntityData(Entity entity) {

        if (entity != null && entity.getEntityData().hasKey(Reference.MOD_ID) && entity.getEntityData().getTag(Reference.MOD_ID) instanceof NBTTagCompound ) {
            return entity.getEntityData().getCompoundTag(Reference.MOD_ID);
        }

        return new NBTTagCompound();
    }

    public static void saveCustomEntityData(Entity entity, NBTTagCompound nbtTagCompound) {

        if (entity != null) {
            entity.getEntityData().setTag(Reference.MOD_ID, nbtTagCompound);
        }
    }
}
