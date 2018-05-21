package com.sparta.is.entity.driveables.types;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;

public class ShootableType extends InfoType
{
    @SideOnly(Side.CLIENT)
    public ModelBase model;

    public int maxStackSize = 1;
    public int roundsFiredPerItem = 1;
    public int entitiesPerRound = 1;
    public int damageVsUnits = 1, damageVsLiving = 1;
    public float explosionRadius = 0f;
    public boolean explosionBreaksBlocks = false;
    public float bulletSpread;
    public int numBullets = 1;
    public String dropItemOnReload = null, dropItemOnShoot = null, dropItemOnHit = null;
    public String trailParticleType = "smoke";
    public boolean trailParticles = false;

    public static HashMap<Integer, ShootableType> shootables = new HashMap<Integer, ShootableType>();

    public ShootableType(TypeFile file)
    {
        super(file);
    }

    public static ShootableType getShootableType(String string)
    {
        return shootables.get(string.hashCode());
    }

    public static ShootableType getShootableType(int hash)
    {
        return shootables.get(hash);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBase getModel()
    {
        return model;
    }

}
