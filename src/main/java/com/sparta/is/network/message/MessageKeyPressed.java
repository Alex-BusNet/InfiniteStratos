package com.sparta.is.network.message;

import com.sparta.is.reference.Key;
import com.sparta.is.utility.IKeyBound;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage>
{
    private byte keyPressed;

    public MessageKeyPressed()
    {
    }

    public MessageKeyPressed(Key key)
    {
        if (key == Key.STANDBY)
        {
            this.keyPressed = (byte) Key.STANDBY.ordinal();
        }
        else if (key == Key.PARTIAL_DEPLOY)
        {
            this.keyPressed = (byte) Key.PARTIAL_DEPLOY.ordinal();
        }
        else if (key == Key.FULL_DEPLOY)
        {
            this.keyPressed = (byte) Key.FULL_DEPLOY.ordinal();
        }
        else if(key == Key.ONE_OFF_ABILITY)
        {
            this.keyPressed = (byte) Key.ONE_OFF_ABILITY.ordinal();
        }
        else if(key == Key.ONE_OFF_ABILITY_OFF)
        {
            this.keyPressed = (byte) Key.ONE_OFF_ABILITY_OFF.ordinal();
        }
        else if(key == Key.UNKNOWN)
        {
            this.keyPressed = (byte) Key.UNKNOWN.ordinal();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.keyPressed = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(keyPressed);
    }

    @Override
    public IMessage onMessage(MessageKeyPressed message, MessageContext ctx)
    {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

        if (entityPlayer != null && entityPlayer.getCurrentArmor(2) != null && entityPlayer.getCurrentArmor(2).getItem() instanceof IKeyBound)
        {
            if (message.keyPressed == Key.STANDBY.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentArmor(2).getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentArmor(2), Key.STANDBY, false);
            }
            else if (message.keyPressed == Key.PARTIAL_DEPLOY.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentArmor(2).getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentArmor(2), Key.PARTIAL_DEPLOY, false);
            }
            else if (message.keyPressed == Key.FULL_DEPLOY.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentArmor(2).getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentArmor(2), Key.FULL_DEPLOY, false);
            }
            else if(message.keyPressed == Key.ONE_OFF_ABILITY.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.ONE_OFF_ABILITY, false);
            }
            else if(message.keyPressed == Key.ONE_OFF_ABILITY_OFF.ordinal())
            {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key.ONE_OFF_ABILITY_OFF, false);
            }
        }

        return null;
    }
}
