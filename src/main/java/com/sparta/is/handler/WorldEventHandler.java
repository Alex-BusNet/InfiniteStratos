package com.sparta.is.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.relauncher.Side;

public class WorldEventHandler
{
    public static boolean hasInitialized = false;

    @SubscribeEvent
    public void onWorldLoadEvent(WorldEvent.Load event)
    {
        if(!hasInitialized && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
        {
            hasInitialized = true;
        }
    }

}
