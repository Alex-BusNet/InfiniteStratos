package com.sparta.is.network.message;


import com.sparta.is.tileentity.TileEntityIS;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class MessageTileEntityIS implements IMessage, IMessageHandler<MessageTileEntityIS, IMessage>
{
    public int x, y, z;
    public byte orientation, state;
    public String customName;
    public UUID ownerUUID;

    public MessageTileEntityIS()
    {
    }

    public MessageTileEntityIS(TileEntityIS tileEntityIS)
    {
        this.x = tileEntityIS.xCoord;
        this.y = tileEntityIS.yCoord;
        this.z = tileEntityIS.zCoord;
        this.orientation = (byte) tileEntityIS.getOrientation().ordinal();
        this.state = (byte) tileEntityIS.getState();
        this.customName = tileEntityIS.getCustomName();
        this.ownerUUID = tileEntityIS.getOwnerUUID();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        if (buf.readBoolean())
        {
            this.ownerUUID = new UUID(buf.readLong(), buf.readLong());
        }
        else
        {
            this.ownerUUID = null;
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        if (ownerUUID != null)
        {
            buf.writeBoolean(true);
            buf.writeLong(ownerUUID.getMostSignificantBits());
            buf.writeLong(ownerUUID.getLeastSignificantBits());
        }
        else
        {
            buf.writeBoolean(false);
        }
    }

    @Override
    public IMessage onMessage(MessageTileEntityIS message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityIS)
        {
            ((TileEntityIS) tileEntity).setOrientation(message.orientation);
            ((TileEntityIS) tileEntity).setState(message.state);
            ((TileEntityIS) tileEntity).setCustomName(message.customName);
            ((TileEntityIS) tileEntity).setOwnerUUID(message.ownerUUID);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityIS - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, ownerUUID:%s", x, y, z, orientation, state, customName, ownerUUID);
    }
}
