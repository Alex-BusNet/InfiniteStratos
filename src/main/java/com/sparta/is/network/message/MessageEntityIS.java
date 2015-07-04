package com.sparta.is.network.message;

import com.sparta.is.entity.EntityIS;
import com.sparta.is.entity.EntityISTabane;
import com.sparta.is.network.PacketHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class MessageEntityIS implements IMessage, IMessageHandler<MessageEntityIS, IMessage>
{
    public int id, profession;
    public String customName;

    public MessageEntityIS()
    {

    }

    public MessageEntityIS(Entity entity)
    {
        this.id = entity.getEntityId();

        if(entity instanceof EntityIS) {
            this.customName = ((EntityIS) entity).getCustomNameTag();
            this.profession = ((EntityIS)entity).getProfession();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        this.id = buf.readInt();
        this.profession = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        buf.writeByte(id);
        buf.writeByte(profession);
    }

    @Override
    public IMessage onMessage(MessageEntityIS message, MessageContext ctx)
    {
        EntityIS entityIS = new EntityIS(FMLClientHandler.instance().getWorldClient());
        Entity entity = FMLClientHandler.instance().getClient().theWorld.getEntityByID(entityIS.getEntityId());

        PacketHandler.INSTANCE.sendToServer(new MessageEntityIS(entity));

        if (entity instanceof EntityISTabane)
        {
            entity.setEntityId(message.id);
            ((EntityISTabane)entity).setCustomNameTag(message.customName);
            ((EntityISTabane)entity).setProfession(message.profession);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageEntityTabane - id:%s, customName:%s, profession:%s", id, customName);
    }
}
