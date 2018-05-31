package com.sparta.is.core.tileentity;

import com.sparta.is.core.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;

import javax.annotation.Nonnull;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;

public class TileEntityIS extends TileEntity implements ITickable
{
    private EnumFacing forward = null;
    private  EnumFacing up = null;

    private static final ThreadLocal<WeakReference<TileEntityIS>> DROP_NO_ITEMS = new ThreadLocal<>();

    protected byte state;
    protected String customName;
    protected UUID ownerUUID;

    public TileEntityIS()
    {
        this("");
    }

    public TileEntityIS(String name)
    {
//        orientation = EnumFacing.SOUTH;
        state = 0;
        customName = name;
        ownerUUID = null;
    }

    @Nonnull
    public TileEntity getTile()
    {
        return this;
    }

    public void setOrientation(EnumFacing fwd, EnumFacing up)
    {
        this.forward = fwd;
        this.up = up;
    }

    public byte getState()
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

    public void setCustomName(String name)
    {
        customName = name;
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
            this.forward = EnumFacing.getFront(nbtTagCompound.getByte(Names.NBT.DIRECTION));
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

        nbtTagCompound.setByte(Names.NBT.DIRECTION, (byte) forward.ordinal());
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
//    public Packet getDescriptionPacket()
//    {
//        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityIS(this));
//    }

    @Override
    public void update()
    {

    }

//    @Override
    public EnumFacing getForward()
    {
        if(this.forward == null)
        {
            return EnumFacing.NORTH;
        }

        return this.forward;
    }

    public EnumFacing getUp()
    {
        if(this.up == null)
        {
            return EnumFacing.UP;
        }

        return this.up;
    }


    public boolean dropItems()
    {
        final WeakReference<TileEntityIS> what = DROP_NO_ITEMS.get();
        return what == null || what.get() != this;
    }

    public void getDrops( final World w, final BlockPos pos, final List<ItemStack> drops )
    {

    }

    public void getNoDrops(final World w, final BlockPos pos, final List<ItemStack> drops )
    {

    }
}
