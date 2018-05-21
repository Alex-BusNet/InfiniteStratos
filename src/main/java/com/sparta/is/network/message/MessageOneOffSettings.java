package com.sparta.is.network.message;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.settings.OneOffSettings;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageOneOffSettings implements IMessage
{
    private boolean oneOffActive;

    public MessageOneOffSettings()
    {
        oneOffActive = false;
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

    public static class MessageHandler implements IMessageHandler<MessageOneOffSettings, IMessage>
    {
        @Override
        public IMessage onMessage(MessageOneOffSettings message, MessageContext ctx)
        {
            InfiniteStratos.proxy.getClientProxy().oneOffSettings = new OneOffSettings(message.oneOffActive);
            return null;
        }
    }
}
