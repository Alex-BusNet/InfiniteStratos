package com.sparta.is.client.handler;

import com.sparta.is.core.armor.ArmorIS;
import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.core.network.Network;
import com.sparta.is.core.network.message.MessageKeyPressed;
import com.sparta.is.core.reference.Key;
import com.sparta.is.core.utils.interfaces.IKeyBound;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler
{
    private static Key getPressedKeyBinding()
    {
        if ( KeyBindings.STANDBY.isPressed() )
        {
            return Key.STANDBY;
        }
        else if ( KeyBindings.PARTIAL_DEPLOY.isPressed() )
        {
            return Key.PARTIAL_DEPLOY;
        }
        else if ( KeyBindings.FULL_DEPLOY.isPressed() )
        {
            return Key.FULL_DEPLOY;
        }
        else if ( KeyBindings.EQUALIZER_ACCESS.isPressed() )
        {
            return Key.EQUALIZER_ACCESS_MODIFIER;
        }
        else if ( KeyBindings.ONE_OFF.isPressed() )
        {
            return Key.ONE_OFF_ABILITY;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        Key key = getPressedKeyBinding();

        if(key == Key.UNKNOWN)
        {
            return;
        }

        LogHelper.info(LogHelper.MOD_MARKER, key.name());

        if(key == Key.ONE_OFF_ABILITY)
        {
            if(FMLClientHandler.instance().getClient().inGameHasFocus && FMLClientHandler.instance().getClientPlayerEntity() != null)
            {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                if(entityPlayer.getHeldItemMainhand() != ItemStack.EMPTY && entityPlayer.getHeldItemMainhand().getItem() instanceof IKeyBound)
                {
                    if ( entityPlayer.getEntityWorld().isRemote )
                    {
                        Network.INSTANCE.sendToServer(new MessageKeyPressed(key));
                    }
                    else
                    {
                        ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, key, false);
                    }
                }
            }
        }
        else if(key == Key.FULL_DEPLOY || key == Key.PARTIAL_DEPLOY || key == Key.STANDBY)
        {
            if ( FMLClientHandler.instance().getClient().inGameHasFocus && FMLClientHandler.instance().getClientPlayerEntity() != null )
            {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                if ( entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ArmorIS && entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof IKeyBound )
                {
                    if ( entityPlayer.getEntityWorld().isRemote )
                    {
                        Network.INSTANCE.sendToServer(new MessageKeyPressed(key));
                    }
                    else
                    {
                        ((IKeyBound) entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).doKeyBindingAction(entityPlayer, key, false);
                    }
                }
            }
        }
    }
}
