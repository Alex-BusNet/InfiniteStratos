package com.sparta.is.network.message;

import com.sparta.is.reference.Key;
import com.sparta.is.utils.IKeyBound;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyPressed implements IMessage
{
    private Key keyPressed;

    public MessageKeyPressed()
    {
    }

    public MessageKeyPressed(Key keyPressed)
    {
        if (keyPressed != null)
        {
            this.keyPressed = keyPressed;
        }
        else
        {
            this.keyPressed = Key.UNKNOWN;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.keyPressed = Key.getKey(buf.readByte());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte((byte) keyPressed.ordinal());
    }

    public static class MessageHandler implements IMessageHandler<MessageKeyPressed, IMessage>
    {
        @Override
        public IMessage onMessage(MessageKeyPressed message, MessageContext ctx)
        {
            EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

            if(entityPlayer != null)
            {
                if(message.keyPressed == Key.ONE_OFF_ABILITY)
                {
                    if ( entityPlayer.getHeldItemMainhand() != ItemStack.EMPTY && entityPlayer.getHeldItemMainhand().getItem() instanceof IKeyBound )
                    {
                        ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, message.keyPressed, false);
                    }
                }
                else if(message.keyPressed == Key.FULL_DEPLOY || message.keyPressed == Key.PARTIAL_DEPLOY || message.keyPressed == Key.STANDBY)
                {
                    if ( entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY && entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof IKeyBound )
                    {
                        ((IKeyBound) entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).doKeyBindingAction(entityPlayer, message.keyPressed, false);
                    }
                }
            }

            return null;
        }
    }
}
