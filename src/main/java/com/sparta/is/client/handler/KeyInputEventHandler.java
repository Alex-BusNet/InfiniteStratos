package com.sparta.is.client.handler;

import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.network.PacketHandler;
import com.sparta.is.network.message.MessageKeyPressed;
import com.sparta.is.reference.Key;
import com.sparta.is.utility.IKeyBound;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler
{
    private static Key getPressedKeyBinding()
    {
        if (KeyBindings.standbyKey.getIsKeyPressed())
        {
            return Key.STANDBY;
        }
        else if (KeyBindings.partialDeployKey.getIsKeyPressed())
        {
            return Key.PARTIAL_DEPLOY;
        }
        else if (KeyBindings.fullDeployKey.getIsKeyPressed())
        {
            return Key.FULL_DEPLOY;
        }
        else if (KeyBindings.equalizerAccessModifier.getIsKeyPressed())
        {
            return Key.EQUALIZER_ACCESS_MODIFIER;
        }
        else if(KeyBindings.oneOffAbility.getIsKeyPressed())
        {
            return Key.ONE_OFF_ABILITY;
        }
        else if (KeyBindings.oneOffAbilityOff.getIsKeyPressed())
        {
            return Key.ONE_OFF_ABILITY_OFF;
        }

           return Key.UNKNOWN;

    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        if(getPressedKeyBinding() == Key.UNKNOWN)
        {
                return;
        }

        if(getPressedKeyBinding() == Key.ONE_OFF_ABILITY || getPressedKeyBinding() == Key.ONE_OFF_ABILITY_OFF)
        {
            if(FMLClientHandler.instance().getClient().inGameHasFocus)
            {
                if(FMLClientHandler.instance().getClientPlayerEntity() != null)
                {
                    EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                    if(entityPlayer.getCurrentEquippedItem() != null)
                    {
                        ItemStack currentEquippedItem = entityPlayer.getCurrentEquippedItem();

                        if(currentEquippedItem.getItem() instanceof IKeyBound)
                        {
                            if(entityPlayer.worldObj.isRemote)
                            {
                                PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeyBinding()));
                            }
                            else
                            {
                                ((IKeyBound) currentEquippedItem.getItem()).doKeyBindingAction(entityPlayer, currentEquippedItem, getPressedKeyBinding());
                            }
                        }
                    }
                }
            }
        }
        else if(FMLClientHandler.instance().getClient().inGameHasFocus)
        {
            if(FMLClientHandler.instance().getClientPlayerEntity() != null)
            {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                if(entityPlayer.getCurrentArmor(2) != null)
                {
                    ItemStack currentEquippedArmor = entityPlayer.getCurrentArmor(2);

                    if(currentEquippedArmor.getItem() instanceof IKeyBound)
                    {
                        if(entityPlayer.worldObj.isRemote)
                        {
                            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeyBinding()));
                        }
                        else
                        {
                            ((IKeyBound) currentEquippedArmor.getItem()).doKeyBindingAction(entityPlayer, currentEquippedArmor, getPressedKeyBinding());
                        }
                    }
                }
            }
        }
    }
}
