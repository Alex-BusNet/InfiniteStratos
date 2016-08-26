package com.sparta.is.tileentity;

import com.sparta.is.network.PacketHandler;
import com.sparta.is.network.message.MessageTileEntityIS;
import com.sparta.is.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.UsernameCache;

import javax.annotation.Nonnull;
import java.util.UUID;

public class TileEntityIS extends TileEntity implements ITickable
{
    protected EnumFacing orientation;
    protected byte state;
    protected String customName;
    protected UUID ownerUUID;

    public TileEntityIS()
    {
        orientation = EnumFacing.SOUTH;
        state = 0;
        customName = "";
        ownerUUID = null;
    }

//    public boolean notLoaded(BlockPos blockPos)
//    {
//        return !this.worldObj.blockExists(blockPos);
//    }

    @Nonnull
    public TileEntity getTile()
    {
        return this;
    }


    public EnumFacing getOrientation()
    {
        return orientation;
    }

    public void setOrientation(EnumFacing orientation)
    {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = EnumFacing.getFront(orientation);
    }

    public short getState()
    {
        return state;
    }

    public void setState(byte state)
    {
        this.state = state;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public UUID getOwnerUUID()
    {
        return ownerUUID;
    }

    public String getOwnerName()
    {
        if (ownerUUID != null)
        {
            return UsernameCache.getLastKnownUsername(ownerUUID);
        }

        return "Unknown";
    }

    public void setOwner(EntityPlayer entityPlayer)
    {
        this.ownerUUID = entityPlayer.getPersistentID();
    }

    public void setOwnerUUID(UUID ownerUUID)
    {
        this.ownerUUID = ownerUUID;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.DIRECTION))
        {
            this.orientation = EnumFacing.getFront(nbtTagCompound.getByte(Names.NBT.DIRECTION));
        }

        if (nbtTagCompound.hasKey(Names.NBT.STATE))
        {
            this.state = nbtTagCompound.getByte(Names.NBT.STATE);
        }

        if (nbtTagCompound.hasKey(Names.NBT.CUSTOM_NAME))
        {
            this.customName = nbtTagCompound.getString(Names.NBT.CUSTOM_NAME);
        }

        if (nbtTagCompound.hasKey(Names.NBT.OWNER_UUID_MOST_SIG) && nbtTagCompound.hasKey(Names.NBT.OWNER_UUID_LEAST_SIG))
        {
            this.ownerUUID = new UUID(nbtTagCompound.getLong(Names.NBT.OWNER_UUID_MOST_SIG), nbtTagCompound.getLong(Names.NBT.OWNER_UUID_LEAST_SIG));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Names.NBT.DIRECTION, (byte) orientation.ordinal());
        nbtTagCompound.setByte(Names.NBT.STATE, state);

        if (this.hasCustomName())
        {
            nbtTagCompound.setString(Names.NBT.CUSTOM_NAME, customName);
        }

        if (this.hasOwner())
        {
            nbtTagCompound.setLong(Names.NBT.OWNER_UUID_MOST_SIG, ownerUUID.getMostSignificantBits());
            nbtTagCompound.setLong(Names.NBT.OWNER_UUID_LEAST_SIG, ownerUUID.getLeastSignificantBits());
        }

        if( this.customName != null )
        {
            nbtTagCompound.setString( "customName", this.customName );
        }

        return nbtTagCompound;
    }

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public boolean hasOwner()
    {
        return ownerUUID != null;
    }

//    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityIS(this));
    }

    @Override
    public void update()
    {

    }
}

