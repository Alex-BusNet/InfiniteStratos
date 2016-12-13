package com.sparta.is.handler;

import com.sparta.is.network.Network;
import com.sparta.is.network.message.MessageOneOffSettings;
import com.sparta.is.network.message.MessageUnitSettings;
import com.sparta.is.settings.OneOffSettings;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.utils.EntityHelper;
import com.sparta.is.utils.LogHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;


public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if(event.player != null)
        {
            NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(event.player);

            //Unit Settings
            UnitSettings unitSettings = new UnitSettings();
            unitSettings.readFromNBT(playerCustomData);
            unitSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(event.player, playerCustomData);

            if(event.player.getEntityWorld().isRemote)
            {
                Network.INSTANCE.sendTo(new MessageUnitSettings(unitSettings), (EntityPlayerMP) event.player);
            }

            //OneOff Settings
            OneOffSettings oneOffSettings = new OneOffSettings();
            oneOffSettings.readFromNBT(playerCustomData);
            oneOffSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(event.player, playerCustomData);

            if(event.player.getEntityWorld().isRemote)
            {
                Network.INSTANCE.sendTo(new MessageOneOffSettings(oneOffSettings), (EntityPlayerMP) event.player);
            }

            LogHelper.info("Player Data: " + playerCustomData);
        }
    }

}
