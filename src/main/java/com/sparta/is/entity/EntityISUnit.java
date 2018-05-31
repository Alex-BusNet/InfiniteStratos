package com.sparta.is.entity;

import com.sparta.is.entity.driveables.DriveableData;
import com.sparta.is.entity.driveables.DriveablePart;
import com.sparta.is.entity.driveables.DriveableType;
import com.sparta.is.entity.driveables.RotatedAxes;
import com.sparta.is.entity.driveables.types.UnitType;
import com.sparta.is.core.utils.interfaces.IOwnable;
import com.sparta.is.core.utils.Vector3i;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.lwjgl.util.vector.Vector3f;

public class EntityISUnit extends EntityUnitController implements IOwnable
{
    public UnitType type;
    private int ticksSinceUsed;
    public int toggleTimer = 0;
    protected float moveX = 0;
    protected float moveZ = 0;
    public RotatedAxes legAxes;
    public float prevLegsYaw = 0F;
    private int jumpDelay = 0;
//    public UnitInventory inventory;
    public float legSwing = 0;
    /** Used for shooting guns */
    public float shootDelayLeft = 0, shootDelayRight = 0;
    /** Used for gun sounds */
    public int soundDelayLeft = 0, soundDelayRight = 0;
    /** The coords of the blocks being destroyed */
    public Vector3i breakingBlock = null;
    /** Progress made towards breaking each block */
    public float breakingProgress = 0F;
    /** Timer for the RocketPack Sound */
    private float rocketTimer = 0F;
    private int diamondTimer = 0;

    /** Gun animations */
//    public GunAnimations leftAnimations = new GunAnimations(), rightAnimations = new GunAnimations();
//    boolean couldNotFindFuel;

    public EntityPlayer placer;

    public EntityISUnit(World world)
    {
        super(world);
        setSize(2f, 3f);
        stepHeight = 3;
        legAxes = new RotatedAxes();
//        inventory = new UnitInventory(this);
    }

    public EntityISUnit(World world, double x, double y, double z, UnitType type, DriveableData data, NBTTagCompound tags)
    {
        super(world, type, data);
        legAxes = new RotatedAxes();
        setSize(2f, 3f);
        stepHeight = 3;
        setPosition(x, y, z);
        initType(type, false);
//        inventory = new UnitInventory(this, tags);
    }

    public EntityISUnit(World world, double x, double y, double z, EntityPlayer player, UnitType type, DriveableData data, NBTTagCompound tags)
    {
        this(world, x, y, z, type, data, tags);
        turn(player.rotationYaw,90F);
        legAxes.rotateGlobalYaw(placer.rotationYaw + 90f);
        prevLegsYaw = legAxes.getYaw();
        this.placer = player;
    }

    @Override
    protected void initType(DriveableType type, boolean clientSide)
    {
        super.initType(type, clientSide);
        setSize(((UnitType)type).width, ((UnitType)type).height);
        stepHeight = ((UnitType)type).stepHeight;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setFloat("LegsYaw", legAxes.getYaw());
//        tag.setTag("Inventory", inventory.writeToNBT(new NBTTagCompound()));
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        legAxes.setAngles(tag.getFloat("LegsYaw"), 0, 0);
//        inventory.readFromNBT(tag.getCompoundTag("Inventory"));
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        super.writeSpawnData(data);
//        ByteBufUtils.writeTag(data, inventory.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        super.readSpawnData(data);
        legAxes.rotateGlobalYaw(axes.getYaw());
        prevLegsYaw = legAxes.getYaw();

//        inventory.readFromNBT(ByteBufUtils.readTag(data));
    }

    @Override
    public double getYOffset()
    {
        return yOffset;
    }

    @Override
    protected void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part)
    {

    }

    @Override
    public boolean hasMouseControlMode()
    {
        return false;
    }

    @Override
    public void onMouseMoved(int deltaX, int deltaY)
    {
    }

    @Override
    public boolean pressKey(int key, EntityPlayer player)
    {
        return false;
    }

    @Override
    public void updateKeyHeldState(int key, boolean held)
    {

    }

    @Override
    public float getPlayerRoll()
    {
        return 0;
    }

    @Override
    public float getPrevPlayerRoll()
    {
        return 0;
    }

    @Override
    public float getCameraDistance()
    {
        return  0.0f;
    }

    public UnitType getUnitType()
    {
        return type;
    }

}
