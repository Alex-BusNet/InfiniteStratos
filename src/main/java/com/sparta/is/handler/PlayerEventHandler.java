package com.sparta.is.handler;

import com.sparta.is.network.PacketHandler;
import com.sparta.is.network.message.MessageChalkSettings;
import com.sparta.is.network.message.MessageOneOffSettings;
import com.sparta.is.network.message.MessageUnitSettings;
import com.sparta.is.settings.ChalkSettings;
import com.sparta.is.settings.OneOffSettings;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.utility.EntityHelper;
import com.sparta.is.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerLoggedIn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.player != null)
        {
            NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(event.player);

            // Chalk Settings
            ChalkSettings chalkSettings = new ChalkSettings();
            chalkSettings.readFromNBT(playerCustomData);
            chalkSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(event.player, playerCustomData);
            PacketHandler.INSTANCE.sendTo(new MessageChalkSettings(chalkSettings), (EntityPlayerMP) event.player);

            //Unit Settings
            UnitSettings unitSettings = new UnitSettings();
            unitSettings.readFromNBT(playerCustomData);
            unitSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(event.player, playerCustomData);
            PacketHandler.INSTANCE.sendTo(new MessageUnitSettings(unitSettings), (EntityPlayerMP) event.player);

            //OneOff Settings
            OneOffSettings oneOffSettings = new OneOffSettings();
            oneOffSettings.readFromNBT(playerCustomData);
            oneOffSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(event.player, playerCustomData);
            PacketHandler.INSTANCE.sendTo(new MessageOneOffSettings(oneOffSettings), (EntityPlayerMP) event.player);

            LogHelper.info("Player Data: " + playerCustomData);
        }
        else
        {
            LogHelper.info("No player");
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event)
    {
        LogHelper.info("Player logged out");
    }

    @SubscribeEvent
    public void onLivingFallEvent(LivingFallEvent lfe)
    {
//        LogHelper.info("LivingFall Event Called");

        if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
        {
            if(EntityHelper.isUnitEquipped(lfe.entityLiving, 1))
            {
                lfe.distance = 0.0f;
            }
        }
    }

}
