package com.sparta.is.core.integration.waila;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Waila
{
    static final String modid = "Waila";
    static final String PulseId = modid + "Integration";

    @SubscribeEvent
    public void preInit(FMLPreInitializationEvent event)
    {
        FMLInterModComms.sendMessage(modid, "register", "com.sparta.is.core.integration.waila.WailaRegistrar.wailaCallback");
    }
}
