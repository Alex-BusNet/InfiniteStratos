package com.sparta.is.network.message;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.settings.OneOffSettings;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageOneOffSettings implements IMessage, IMessageHandler<MessageOneOffSettings, IMessage>
{
    private boolean oneOffActive;

    public MessageOneOffSettings()
    {

    }

    public MessageOneOffSettings(OneOffSettings oneOffSettings)
    {
        this.oneOffActive = oneOffSettings.isOneOffActive();
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.oneOffActive = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(this.oneOffActive);
    }

    @Override
    public IMessage onMessage(MessageOneOffSettings message, MessageContext ctx)
    {
        InfiniteStratos.proxy.getClientProxy().oneOffSettings = new OneOffSettings(message.oneOffActive);
        return null;
    }
}
