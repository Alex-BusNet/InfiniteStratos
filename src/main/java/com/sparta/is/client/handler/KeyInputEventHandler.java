package com.sparta.is.client.handler;

import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.network.PacketHandler;
import com.sparta.is.network.message.MessageKeyPressed;
import com.sparta.is.reference.Key;
import com.sparta.is.utility.IKeyBound;
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
        if (KeyBindings.standbyKey.isPressed())
        {
            return Key.STANDBY;
        }
        else if (KeyBindings.partialDeployKey.isPressed())
        {
            return Key.PARTIAL_DEPLOY;
        }
        else if (KeyBindings.fullDeployKey.isPressed())
        {
            return Key.FULL_DEPLOY;
        }
        else if (KeyBindings.equalizerAccessModifier.isPressed())
        {
            return Key.EQUALIZER_ACCESS_MODIFIER;
        }
        else if(KeyBindings.oneOffAbility.isPressed())
        {
            return Key.ONE_OFF_ABILITY;
        }
        else if (KeyBindings.oneOffAbilityOff.isPressed())
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

                    if(entityPlayer.getHeldItemMainhand() != null)
                    {
                        ItemStack currentEquippedItem = entityPlayer.getHeldItemMainhand();

                        if(currentEquippedItem.getItem() instanceof IKeyBound)
                        {
                            if(entityPlayer.worldObj.isRemote)
                            {
                                PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeyBinding()));
                            }
                            else
                            {
                                ((IKeyBound) currentEquippedItem.getItem()).doKeyBindingAction(entityPlayer, currentEquippedItem, getPressedKeyBinding(), false);
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

                if(entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST)!= null)
                {
                    ItemStack currentEquippedArmor = entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

                    if(currentEquippedArmor.getItem() instanceof IKeyBound)
                    {
                        if(entityPlayer.worldObj.isRemote)
                        {
                            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeyBinding()));
                        }
                        else
                        {
                            ((IKeyBound) currentEquippedArmor.getItem()).doKeyBindingAction(entityPlayer, currentEquippedArmor, getPressedKeyBinding(), false);
                        }
                    }
                }
            }
        }
    }
}
