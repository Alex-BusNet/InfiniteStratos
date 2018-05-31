package com.sparta.is.core.entity;

import com.sparta.is.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityIS extends Entity
{
    public EntityIS(World world)
    {
        super(world);
        ModEntities.registerEntity(this);
    }

    @Override
    protected void entityInit()
    {
        return;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {

    }
}
