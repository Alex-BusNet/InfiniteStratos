package com.sparta.is.core.handler;

import com.sparta.is.core.network.Network;
import com.sparta.is.core.network.message.MessageOneOffSettings;
import com.sparta.is.core.network.message.MessageUnitSettings;
import com.sparta.is.core.settings.OneOffSettings;
import com.sparta.is.core.settings.UnitSettings;
import com.sparta.is.core.utils.helpers.EntityHelper;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashMap;
import java.util.Map;


public class PlayerEventHandler
{

    public static Map<String, PlayerData> serverSideData = new HashMap<String, PlayerData>();
    public static Map<String, PlayerData> clientSideData = new HashMap<String, PlayerData>();

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

//    public void serverTick()
//    {
//        for(WorldServer world : MinecraftServer.getServer().worldServers)
//        {
//            for(Object player : world.playerEntities)
//            {
//                getPlayerData((EntityPlayer)player).tick((EntityPlayer)player);
//            }
//        }
//    }

    public void clientTick()
    {
        if( Minecraft.getMinecraft().world != null)
        {
            for(Object player : Minecraft.getMinecraft().world.playerEntities)
            {
                getPlayerData((EntityPlayer)player).tick((EntityPlayer)player);
            }
        }
    }

    public static PlayerData getPlayerData(EntityPlayer player)
    {
        if(player == null)
            return null;
        return getPlayerData(player.getName(), player.world.isRemote ? Side.CLIENT : Side.SERVER);
    }

    public static PlayerData getPlayerData(String username)
    {
        return getPlayerData(username, Side.SERVER);
    }

    public static PlayerData getPlayerData(EntityPlayer player, Side side)
    {
        if(player == null)
            return null;
        return getPlayerData(player.getName(), side);
    }

    public static PlayerData getPlayerData(String username, Side side)
    {
        if(side.isClient())
        {
            if(!clientSideData.containsKey(username))
                clientSideData.put(username, new PlayerData(username));
        }
        else
        {
            if(!serverSideData.containsKey(username))
                serverSideData.put(username, new PlayerData(username));
        }
        return side.isClient() ? clientSideData.get(username) : serverSideData.get(username);
    }

}
