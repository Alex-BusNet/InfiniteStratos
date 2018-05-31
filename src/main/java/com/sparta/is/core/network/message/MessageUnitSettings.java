package com.sparta.is.core.network.message;

import com.sparta.is.core.InfiniteStratos;
import com.sparta.is.core.settings.UnitSettings;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUnitSettings implements IMessage
{
    public UnitSettings unitSettings;

    public MessageUnitSettings()
    {
        unitSettings = new UnitSettings();
    }

    public MessageUnitSettings(UnitSettings unitSettings)
    {
        this.unitSettings = unitSettings;
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.unitSettings = new UnitSettings(buf.readInt(), buf.readInt(), new String(buf.readBytes(buf.readInt()).array()), new String(buf.readBytes(buf.readInt()).array()));
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(unitSettings.getTotalEqualizers());
        buf.writeInt(unitSettings.getDeployedState());
        buf.writeInt(unitSettings.getOwnerName().length());
        buf.writeBytes(unitSettings.getOwnerName().getBytes());
        buf.writeInt(unitSettings.getUnitName().length());
        buf.writeBytes(unitSettings.getUnitName().getBytes());
    }

    public static class MessageHandler implements IMessageHandler<MessageUnitSettings, IMessage>
    {
        @Override
        public IMessage onMessage(MessageUnitSettings message, MessageContext ctx)
        {
            InfiniteStratos.proxy.getClientProxy().unitSettings = message.unitSettings;
            return null;
        }
    }
}
