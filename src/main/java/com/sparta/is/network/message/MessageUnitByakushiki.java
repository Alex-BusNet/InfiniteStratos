package com.sparta.is.network.message;

import com.sparta.is.armor.UnitByakushiki;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class MessageUnitByakushiki implements IMessage, IMessageHandler<MessageUnitByakushiki, IMessage>
{
    public String customName;
    public UUID ownerUUID;
    public String ownerName;
    public int state;

    public MessageUnitByakushiki()
    {

    }

    public MessageUnitByakushiki(UnitByakushiki unitByakushiki)
    {
        this.ownerUUID = unitByakushiki.getOwnerUUID();
        this.ownerName = unitByakushiki.getOwnerName();
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        this.state = buf.readInt();
        int ownerNameLength = buf.readInt();
        this.ownerName = new String(buf.readBytes(ownerNameLength).array());
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
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        buf.writeInt(state);
        buf.writeInt(ownerName.length());
        buf.writeBytes(ownerName.getBytes());

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
    public IMessage onMessage(MessageUnitByakushiki message, MessageContext ctx)
    {
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageUnitByakushiki - state:%s, customName:%s, ownerUUID:%s", state, customName, ownerUUID);
    }
}
