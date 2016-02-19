package com.sparta.is.network.message;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.settings.ChalkSettings;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageChalkSettings implements IMessage, IMessageHandler<MessageChalkSettings, IMessage>
{
    private int index, size, rotation;

    public MessageChalkSettings()
    {

    }

    public MessageChalkSettings(ChalkSettings chalkSettings)
    {
        this.index = chalkSettings.getIndex();
        this.size = chalkSettings.getSize();
        this.rotation = chalkSettings.getRotation();
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.index = buf.readInt();
        this.size = buf.readInt();
        this.rotation = buf.readInt();
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.index);
        buf.writeInt(this.size);
        buf.writeInt(this.rotation);
    }

    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param message The message
     * @param ctx
     * @return an optional return message
     */
    @Override
    public IMessage onMessage(MessageChalkSettings message, MessageContext ctx)
    {
        InfiniteStratos.proxy.getClientProxy().chalkSettings = new ChalkSettings(message.index, message.size, message.rotation);
        return null;
    }
}