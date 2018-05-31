package com.sparta.is.entity;

import com.sparta.is.armor.raytracing.Raytracer;
import com.sparta.is.core.entity.EntityIS;
import com.sparta.is.entity.driveables.*;
import com.sparta.is.entity.driveables.types.BulletType;
import com.sparta.is.core.utils.helpers.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;

public abstract class EntityUnitController extends EntityIS implements IControllable, IEntityAdditionalSpawnData
{
    public boolean syncFromServer = true;
    public int serverPositionTransitionTicker;
    public double serverPosX, serverPosY, serverPosZ;
    public double yaw, pitch, roll;
    public float prevRotationRoll;

    public DriveableData driveableData;
    public String driveableType;

    public Vector3f angVel = new Vector3f(0f, 0f, 0f);

    public boolean leftMouseHeld = false, rightMouseHeld = false;
    public float shootDelayPrimary, shootDelaySecondary;
    public int currentGunPrimary, currentGunSecondary;
    public RotatedAxes prevAxes;
    public RotatedAxes axes;

    public EntitySeat[] seats;
    public float yOffset;

    @SideOnly(Side.CLIENT)
    public EntityLivingBase camera;

    private int[] emitterTimers;
    public int animCount = 0;
    public int animFrame = 0;
//
    public EntityUnitController(World world)
    {
        super(world);
        axes = new RotatedAxes();
        prevAxes = new RotatedAxes();
        preventEntitySpawning = true;
        setSize(1F, 1F);
        yOffset = 6f / 16f;
        ignoreFrustumCheck = true;
        setRenderDistanceWeight(200d);
    }

    public EntityUnitController(World world, DriveableType t, DriveableData d)
    {
        this(world);
        driveableType = t.shortName;
        driveableData = d;
    }

    protected void initType(DriveableType type, boolean clientSide)
    {
        seats = new EntitySeat[type.numberOfPassengers + 1];
        for(int i = 0; i < type.numberOfPassengers + 1; i++)
        {
            if(!clientSide)
            {
                seats[i] = new EntitySeat(world, this, i);
                world.spawnEntity(seats[i]);
            }
        }
        yOffset = type.yOffset;

        emitterTimers = new int[type.emitters.size()];
        for(int i = 0; i < type.emitters.size(); i++)
        {
            emitterTimers[i] = rand.nextInt(type.emitters.get(i).emitRate);
        }

        //Register Plane to Radar on Spawning
        //if(type.onRadar == true)
        //	RadarRegistry.register(this);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag)
    {
        driveableData.writeToNBT(tag);
        tag.setString("Type", driveableType);
        tag.setFloat("RotationYaw", axes.getYaw());
        tag.setFloat("RotationPitch", axes.getPitch());
        tag.setFloat("RotationRoll", axes.getRoll());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag)
    {
        driveableType = tag.getString("Type");
        driveableData = new DriveableData(tag);
        initType(DriveableType.getDriveable(driveableType), false);

        prevRotationYaw = tag.getFloat("RotationYaw");
        prevRotationPitch = tag.getFloat("RotationPitch");
        prevRotationRoll = tag.getFloat("RotationRoll");
        axes = new RotatedAxes(prevRotationYaw, prevRotationPitch, prevRotationRoll);
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        ByteBufUtils.writeUTF8String(data, driveableType);

        NBTTagCompound tag = new NBTTagCompound();
        driveableData.writeToNBT(tag);
        ByteBufUtils.writeTag(data, tag);

        data.writeFloat(axes.getYaw());
        data.writeFloat(axes.getPitch());
        data.writeFloat(axes.getRoll());

        //Write damage
        for(EnumDriveablePart ep : EnumDriveablePart.values())
        {
            DriveablePart part = getDriveableData().parts.get(ep);
            data.writeShort((short)part.health);
            data.writeBoolean(part.onFire);
        }
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        try
        {
            driveableType = ByteBufUtils.readUTF8String(data);
            driveableData = new DriveableData(ByteBufUtils.readTag(data));
            initType(getDriveableType(), true);

            axes.setAngles(data.readFloat(), data.readFloat(), data.readFloat());
            prevRotationYaw = axes.getYaw();
            prevRotationPitch = axes.getPitch();
            prevRotationRoll = axes.getRoll();

            //Read damage
            for(EnumDriveablePart ep : EnumDriveablePart.values())
            {
                DriveablePart part = getDriveableData().parts.get(ep);
                part.health = data.readShort();
                part.onFire = data.readBoolean();
            }
        }
        catch(Exception e)
        {
            LogHelper.info(LogHelper.MOD_MARKER, "Failed to retreive plane type from server.");
            super.setDead();
            e.printStackTrace();
        }

        camera = new EntityCamera(world, this);
        world.spawnEntity(camera);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EntityLivingBase getCamera()
    {
        return camera;
    }

    protected boolean canSit(int seat)
    {
        return getDriveableType().numberOfPassengers >= seat && seats[seat].getRidingEntity() == null;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return null;//entity.boundingBox;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public double getMountedYOffset()
    {
        return -0.3D;
    }

    @Override
    public double getYOffset()
    {
        return yOffset;
    }

    @Override
    /** Pass generic damage to the core */
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        return world.isRemote || isDead || attackPart(EnumDriveablePart.core, damagesource, i);
    }

    @Override
    public void setDead()
    {
        super.setDead();

        //Unregister to Radar
        //RadarRegistry.unregister(this);
        if(world.isRemote)
            camera.setDead();

        for(EntitySeat seat : seats)
            if(seat != null)
                seat.setDead();
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        //Do nothing. Like a boss.
        // TODO: perhaps send the player flying??
        //Sounds good. ^
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    @Override
    public void applyEntityCollision(Entity entity)
    {
        if(!isPartOfThis(entity))
            super.applyEntityCollision(entity);
    }

    public boolean isPartOfThis(Entity ent)
    {
        for(EntitySeat seat : seats)
        {
            if(seat == null)
                continue;
            if(ent == seat)
                return true;
            if(seat.getRidingEntity() == ent)
                return true;
        }
        return ent == this;
    }

    public DriveableType getDriveableType()
    {
        return DriveableType.getDriveable(driveableType);
    }

    public DriveableData getDriveableData()
    {
        return driveableData;
    }

    @Override
    public boolean isDead()
    {
        return isDead;
    }

    public boolean attackPart(EnumDriveablePart ep, DamageSource source, float damage)
    {
        DriveablePart part = driveableData.parts.get(ep);
        return part.attack(damage, source.isFireDamage());
    }

    @Override
    public Entity getControllingEntity()
    {
        return seats[0].getControllingEntity();
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target)
    {
        ItemStack stack = new ItemStack(getDriveableType().item, 1, 0);
        NBTTagCompound tags = new NBTTagCompound();
        stack.setTagCompound(tags);
        driveableData.writeToNBT(tags);
        return stack;
    }

    public double getSpeedXYZ()
    {
        return Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
    }

    public double getSpeedXZ()
    {
        return Math.sqrt(motionX * motionX + motionZ * motionZ);
    }

    public ArrayList<Raytracer.BulletHit> attackFromBullet(Vector3f origin, Vector3f motion)
    {
        //Make an array to contain the hits
        ArrayList<Raytracer.BulletHit> hits = new ArrayList<Raytracer.BulletHit>();
        //Get the position of the bullet origin, relative to the centre of the plane, and then rotate the vectors onto local co-ordinates
        Vector3f relativePosVector = Vector3f.sub(origin, new Vector3f((float)posX, (float)posY, (float)posZ), null);
        Vector3f rotatedPosVector = axes.findGlobalVectorLocally(relativePosVector);
        Vector3f rotatedMotVector = axes.findGlobalVectorLocally(motion);
        //Check each part
        for(DriveablePart part : getDriveableData().parts.values())
        {
            //Ray trace the bullet
            Raytracer.DriveableHit hit = part.rayTrace(this, rotatedPosVector, rotatedMotVector);
            if(hit != null)
                hits.add(hit);
        }
        return hits;
    }

    /** Called if the bullet actually hit the part returned by the raytrace
     * @param penetratingPower */
    public float bulletHit(BulletType bulletType, float damage, Raytracer.DriveableHit hit, float penetratingPower)
    {
        DriveablePart part = getDriveableData().parts.get(hit.part);
        part.hitByBullet(bulletType, damage);

        //This is server side bsns
        if(!world.isRemote)
        {
            checkParts();
            //If it hit, send a damage update packet
            //TODO: Update with IS Packet Equivalent
            //FlansMod.getPacketHandler().sendToAllAround(new PacketDriveableDamage(this), posX, posY, posZ, 100, dimension);
        }

        return penetratingPower - 5F;
    }

    /** A simple raytracer for the driveable. Called by tools */
    public DriveablePart raytraceParts(Vector3f origin, Vector3f motion)
    {
        //Get the position of the bullet origin, relative to the centre of the plane, and then rotate the vectors onto local co-ordinates
        Vector3f relativePosVector = Vector3f.sub(origin, new Vector3f((float)posX, (float)posY, (float)posZ), null);
        Vector3f rotatedPosVector = axes.findGlobalVectorLocally(relativePosVector);
        Vector3f rotatedMotVector = axes.findGlobalVectorLocally(motion);
        //Check each part
        for(DriveablePart part : getDriveableData().parts.values())
        {
            //Ray trace the bullet
            if(part.rayTrace(this, rotatedPosVector, rotatedMotVector) != null)
            {
                return part;
            }
        }
        return null;
    }

    /** For overriding for toggles such as gear up / down on planes */
    public boolean canHitPart(EnumDriveablePart part)
    {
        return true;
    }

    /** Internal method for checking that all parts are ok, destroying broken ones, dropping items and making sure that child parts are destroyed when their parents are */
    public void checkParts()
    {
        for(DriveablePart part : getDriveableData().parts.values())
        {
            if(part != null && !part.dead && part.health <= 0 && part.maxHealth > 0)
            {
                killPart(part);
            }
        }


        for(EntitySeat seat : seats)
        {

        }

        //If the core was destroyed, kill the driveable
        if(getDriveableData().parts.get(EnumDriveablePart.core).dead)
        {
            if(!world.isRemote)
            {
                for(DriveablePart part : driveableData.parts.values())
                {
                    if(part.health > 0 && !part.dead)
                        killPart(part);
                }
            }
            setDead();
        }

    }

    private void killPart(DriveablePart part)
    {
        if(part.dead)
            return;
        part.health = 0;
        part.dead = true;

        //Drop items
        DriveableType type = getDriveableType();
        if(!world.isRemote)
        {
            Vector3f pos = new Vector3f(0, 0, 0);

            //Get the midpoint of the part
            if(part.box != null)
                pos = axes.findLocalVectorGlobally(new Vector3f(part.box.x / 16F + part.box.width / 32F, part.box.y / 16F + part.box.height / 32F, part.box.z / 16F + part.box.depth / 32F));

            ArrayList<ItemStack> drops = type.getItemsRequired(part, getDriveableData().core);
            if(drops != null)
            {
                //Drop each itemstack
                for(ItemStack stack : drops)
                {
                    world.spawnEntity(new EntityItem(world, posX + pos.x, posY + pos.y, posZ + pos.z, stack.copy()));
                }
            }

            dropItemsOnPartDeath(pos, part);

            //Inventory is in the core, so drop it if the core is broken
            if(part.type == EnumDriveablePart.core)
            {
                for(int i = 0; i < getDriveableData().getSizeInventory(); i++)
                {
                    ItemStack stack = getDriveableData().getStackInSlot(i);
                    if(stack != null)
                    {
                        world.spawnEntity(new EntityItem(world, posX + rand.nextGaussian(), posY + rand.nextGaussian(), posZ + rand.nextGaussian(), stack));
                    }
                }
            }
        }

        //Kill all child parts to stop things floating unconnected
        for(EnumDriveablePart child : part.type.getChildren())
        {
            killPart(getDriveableData().parts.get(child));
        }
    }

    protected abstract void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part);

    public float getShootDelay(boolean secondary)
    {
        return secondary ? shootDelaySecondary : shootDelayPrimary;
    }


    public int getCurrentGun(boolean secondary)
    {
        return secondary ? currentGunSecondary : currentGunPrimary;
    }

    public void setShootDelay(float f, boolean secondary)
    {
        if(secondary)
            shootDelaySecondary = f;
        else shootDelayPrimary = f;
    }

    public void setCurrentGun(int i, boolean secondary)
    {
        if(secondary)
            currentGunSecondary = i;
        else currentGunPrimary = i;
    }

    @Override
    public String getName()
    {
        return getDriveableType().name;
    }

    public abstract boolean hasMouseControlMode();
}
