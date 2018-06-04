package com.sparta.is.core.network.message;

import com.sparta.is.core.armor.ArmorIS;
import com.sparta.is.core.reference.Key;
import com.sparta.is.core.utils.interfaces.IKeyBound;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
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
            EntityPlayer entityPlayer = ctx.getServerHandler().player;

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
                    ItemStack equippedArmor = ItemStack.EMPTY;
                    for(ItemStack itemStack : entityPlayer.getArmorInventoryList())
                    {
                        if(itemStack.getItem() instanceof ArmorIS )
                        {
                            equippedArmor = itemStack;
                            break;
                        }

                    }

                    if ( equippedArmor != ItemStack.EMPTY && equippedArmor.getItem() instanceof IKeyBound )
                    {
                        ((IKeyBound) equippedArmor.getItem()).doKeyBindingAction(entityPlayer, message.keyPressed, false);
                    }
                }
            }

            return null;
        }
    }
}
