package com.sparta.is.proxy;

import net.minecraftforge.fml.common.event.*;

public interface IProxy
{
    default ClientProxy getClientProxy() { return null; }

    void onPreInit(FMLPreInitializationEvent event);
    void onInit(FMLInitializationEvent event);
    void onPostInit(FMLPostInitializationEvent event);
    void onServerStarting(FMLServerStartingEvent event);
    void onServerStopping(FMLServerStoppingEvent event);
}
