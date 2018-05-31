package com.sparta.is.armor.raytracing;

import com.sparta.is.entity.driveables.EntityBullet;
import com.sparta.is.entity.driveables.RotatedAxes;
import com.sparta.is.entity.driveables.types.BulletType;
import com.sparta.is.entity.driveables.types.EqualizerMeleeType;
import com.sparta.is.entity.driveables.types.InfoType;
import com.sparta.is.core.handler.PlayerData;
import com.sparta.is.core.handler.PlayerEventHandler;
import com.sparta.is.core.item.ItemISMelee;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

public class PlayerHitbox
{
    public EntityPlayer player;
    /** The angles of this box */
    public RotatedAxes axes;
    /** The origin of rotation for this box */
    public Vector3f rP;
    /** The lower left corner of this box */
    public Vector3f o;
    /** The dimensions of this box */
    public Vector3f d;
    /** The type of hitbox */
    public EnumHitboxType type;

    public PlayerHitbox(EntityPlayer player, RotatedAxes axes, Vector3f rotationPoint, Vector3f origin, Vector3f dimensions, EnumHitboxType type)
    {
        this.player = player;
        this.axes = axes;
        this.o = origin;
        this.d = dimensions;
        this.type = type;
        this.rP = rotationPoint;
    }

    @SideOnly(Side.CLIENT)
    public void renderHitbox(World world, Vector3f pos)
    {

        //Vector3f boxOrigin = new Vector3f(pos.x + rP.x, pos.y + rP.y, pos.z + rP.z);
        //world.spawnEntityInWorld(new EntityDebugAABB(world, boxOrigin, d, 2, 1F, 1F, 0F, axes.getYaw(), axes.getPitch(), axes.getRoll(), o));
        if(type != EnumHitboxType.RIGHTARM)
            return;
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                for(int k = 0; k < 3; k++)
                {
                    Vector3f point = new Vector3f(o.x + d.x * i / 2, o.y + d.y * j / 2, o.z + d.z * k / 2);
                    point = axes.findLocalVectorGlobally(point);
//                    world.spawnEntity(new EntityDebugDot(world, new Vector3f(pos.x + rP.x + point.x, pos.y + rP.y + point.y, pos.z + rP.z + point.z), 1, 0F, 1F, 0F));
                }

    }

    public Raytracer.PlayerBulletHit raytrace(Vector3f origin, Vector3f motion)
    {
        //Move to local coords for this hitbox, but don't modify the original "origin" vector
        origin = Vector3f.sub(origin, rP, null);
        origin = axes.findGlobalVectorLocally(origin);
        motion = axes.findGlobalVectorLocally(motion);

        //We now have an AABB starting at o and with dimensions d and our ray in the same coordinate system
        //We are looking for a point at which the ray enters the box, so we need only consider faces that the ray can see. Partition the space into 3 areas in each axis

        //X - axis and faces x = o.x and x = o.x + d.x
        if(motion.x != 0F)
        {
            if(origin.x < o.x) //Check face x = o.x
            {
                float intersectTime = (o.x - origin.x) / motion.x;
                float intersectY = origin.y + motion.y * intersectTime;
                float intersectZ = origin.z + motion.z * intersectTime;
                if(intersectY >= o.y && intersectY <= o.y + d.y && intersectZ >= o.z && intersectZ <= o.z + d.z)
                    return new Raytracer.PlayerBulletHit(this, intersectTime);
            }
            else if(origin.x > o.x + d.x) //Check face x = o.x + d.x
            {
                float intersectTime = (o.x + d.x - origin.x) / motion.x;
                float intersectY = origin.y + motion.y * intersectTime;
                float intersectZ = origin.z + motion.z * intersectTime;
                if(intersectY >= o.y && intersectY <= o.y + d.y && intersectZ >= o.z && intersectZ <= o.z + d.z)
                    return new Raytracer.PlayerBulletHit(this, intersectTime);
            }
        }

        //Z - axis and faces z = o.z and z = o.z + d.z
        if(motion.z != 0F)
        {
            if(origin.z < o.z) //Check face z = o.z
            {
                float intersectTime = (o.z - origin.z) / motion.z;
                float intersectX = origin.x + motion.x * intersectTime;
                float intersectY = origin.y + motion.y * intersectTime;
                if(intersectX >= o.x && intersectX <= o.x + d.x && intersectY >= o.y && intersectY <= o.y + d.y)
                    return new Raytracer.PlayerBulletHit(this, intersectTime);
            }
            else if(origin.z > o.z + d.z) //Check face z = o.z + d.z
            {
                float intersectTime = (o.z + d.z - origin.z) / motion.z;
                float intersectX = origin.x + motion.x * intersectTime;
                float intersectY = origin.y + motion.y * intersectTime;
                if(intersectX >= o.x && intersectX <= o.x + d.x && intersectY >= o.y && intersectY <= o.y + d.y)
                    return new Raytracer.PlayerBulletHit(this, intersectTime);
            }
        }

        //Y - axis and faces y = o.y and y = o.y + d.y
        if(motion.y != 0F)
        {
            if(origin.y < o.y) //Check face y = o.y
            {
                float intersectTime = (o.y - origin.y) / motion.y;
                float intersectX = origin.x + motion.x * intersectTime;
                float intersectZ = origin.z + motion.z * intersectTime;
                if(intersectX >= o.x && intersectX <= o.x + d.x && intersectZ >= o.z && intersectZ <= o.z + d.z)
                    return new Raytracer.PlayerBulletHit(this, intersectTime);
            }
            else if(origin.y > o.y + d.y) //Check face x = o.x + d.x
            {
                float intersectTime = (o.y + d.y - origin.y) / motion.y;
                float intersectX = origin.x + motion.x * intersectTime;
                float intersectZ = origin.z + motion.z * intersectTime;
                if(intersectX >= o.x && intersectX <= o.x + d.x && intersectZ >= o.z && intersectZ <= o.z + d.z)
                    return new Raytracer.PlayerBulletHit(this, intersectTime);
            }
        }

        return null;
    }

    public float hitByBullet(DamageSource source, Entity damageOwner, InfoType firedFrom, BulletType bulletType, float damage, float penetratingPower)
    {
        if(bulletType.setEntitiesOnFire)
            player.setFire(20);
        for(PotionEffect effect : bulletType.hitEffects)
        {
            player.addPotionEffect(new PotionEffect(effect));
        }
        float damageModifier = bulletType.penetratingPower < 0.1F ? penetratingPower / bulletType.penetratingPower : 1;
        switch(type)
        {
            case BODY : break;
            case HEAD : damageModifier *= 2F; break;
            case LEFTARM : damageModifier *= 0.6F; break;
            case RIGHTARM : damageModifier *= 0.6F; break;
            case LEFTITEM : break;
            case RIGHTITEM : break;
            default : break;
        }
        switch(type)
        {
            case BODY :  case HEAD :  case LEFTARM :  case RIGHTARM :
        {
            //Calculate the hit damage
            float hitDamage = damage * bulletType.damageVsLiving * damageModifier;
            //Create a damage source object
            DamageSource damagesource = damageOwner == null ? DamageSource.GENERIC
                    : EntityBullet.GetBulletDamage(firedFrom, bulletType, damageOwner, type == EnumHitboxType.HEAD);

            //When the damage is 0 (such as with Nerf guns) the entityHurt Forge hook is not called, so this hacky thing is here
//            if(!player.worldObj.isRemote && hitDamage == 0 && TeamsManager.getInstance().currentRound != null)
//                TeamsManager.getInstance().currentRound.gametype.playerAttacked((EntityPlayerMP)player, damagesource);

            //Attack the entity!
            if(player.attackEntityFrom(damagesource, hitDamage))
            {
                //If the attack was allowed, we should remove their immortality cooldown so we can shoot them again. Without this, any rapid fire gun become useless
                player.arrowHitTimer++;
                player.hurtResistantTime = player.maxHurtResistantTime / 2;
                //Yuck.
                //PacketDispatcher.sendPacketToAllAround(posX, posY, posZ, 50, dimension, PacketPlaySound.buildSoundPacket(posX, posY, posZ, type.hitSound, true));
            }
            return penetratingPower - 1;
        }
            case RIGHTITEM :
            {
                ItemStack currentStack = player.getActiveItemStack();
                if(currentStack != ItemStack.EMPTY && currentStack.getItem() instanceof ItemISMelee)
                {
                    EqualizerMeleeType gunType = ((ItemISMelee)currentStack.getItem()).getType();
                    //TODO : Shield damage
                    return penetratingPower - gunType.shieldDamageAbsorption;
                }
                else
                    return penetratingPower;
            }
            case LEFTITEM :
            {
                PlayerData data = PlayerEventHandler.getPlayerData(player);
                if(data.offHandGunSlot != 0)
                {
                    ItemStack leftHandStack = null;
                    if(player.world.isRemote && (player != FMLClientHandler.instance().getClient().player))
                        leftHandStack = data.offHandGunStack;
                    else leftHandStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);

                    if(leftHandStack != null && leftHandStack.getItem() instanceof ItemISMelee)
                    {
                        EqualizerMeleeType leftGunType = ((ItemISMelee)leftHandStack.getItem()).getType();
                        //TODO : Shield damage
                        return penetratingPower - leftGunType.shieldDamageAbsorption;
                    }
                }
            }
            default : return penetratingPower;
        }
    }
}
