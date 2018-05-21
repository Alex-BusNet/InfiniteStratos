package com.sparta.is.entity.driveables.types;

import com.sparta.is.entity.driveables.EnumWeaponType;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

import java.util.ArrayList;

public class BulletType extends ShootableType
{
    public boolean canLockOn = false;
    public boolean requiresLockOn = false;
    public EnumWeaponType weaponType = EnumWeaponType.NONE;
    public boolean setEntitiesOnFire = false;
    public float penetratingPower = 1f;
    public ArrayList<PotionEffect> hitEffects = new ArrayList<PotionEffect>();
    public String trailTexture = "defaultBulletTrail";
    public boolean explodeOnImpact = false;
    public boolean hasLight = false;
    public float fallSpeed = 0.0f;
    public float lockOnForce = 0.0f;
    public float maxLockOnAngle = 360.0f;

    public static ArrayList<BulletType> bullets = new ArrayList<BulletType>();

    public BulletType(TypeFile file)
    {
        super(file);
        bullets.add(this);
    }

    public static BulletType getBullet(String s)
    {
        for(BulletType bullet : bullets)
        {
            if(bullet.shortName.equals(s))
                return bullet;
        }
        return null;
    }

    public static BulletType getBullet(Item item)
    {
        for(BulletType bullet : bullets)
        {
            if(bullet.item == item)
                return bullet;
        }
        return null;
    }
}
