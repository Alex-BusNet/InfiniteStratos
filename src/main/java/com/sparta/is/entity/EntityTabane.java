package com.sparta.is.entity;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class EntityTabane extends EntityVillager
{
    public EntityTabane(World world)
    {
        super(world);
        this.setCustomNameTag("Tabane Shinonono");
        this.setAlwaysRenderNameTag(true);
    }

    @Override
    public boolean canDespawn()
    {
        return false;
    }
}
