package com.sparta.is.network.message;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.settings.UnitSettings;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUnitSettings implements IMessage, IMessageHandler<MessageUnitSettings, IMessage>
{
    private int totalEqualizers, deployedState;
    private String ownerName, unitName;

    public MessageUnitSettings()
    {

    }

    public MessageUnitSettings(UnitSettings unitSettings)
    {
        this.totalEqualizers = unitSettings.getTotalEqualizers();
        this.deployedState = unitSettings.getTotalEqualizers();
        this.ownerName = unitSettings.getOwnerName();
        this.unitName = unitSettings.getUnitName();
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.totalEqualizers = buf.readInt();
        this.deployedState = buf.readInt();
        int ownerNameLength = buf.readInt();
        this.ownerName = new String(buf.readBytes(ownerNameLength).array());
        int unitNameLength = buf.readInt();
        this.unitName = new String(buf.readBytes(unitNameLength).array());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.totalEqualizers);
        buf.writeInt(this.deployedState);
        buf.writeInt(this.ownerName.length());
        buf.writeBytes(this.ownerName.getBytes());
        buf.writeInt(this.unitName.length());
        buf.writeBytes(this.unitName.getBytes());
    }

    @Override
    public IMessage onMessage(MessageUnitSettings message, MessageContext ctx)
    {
        InfiniteStratos.proxy.getClientProxy().unitSettings = new UnitSettings(message.totalEqualizers, message.deployedState, message.ownerName, message.unitName);
        return null;
    }
}
