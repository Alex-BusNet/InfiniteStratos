package com.sparta.is.core.handler;

import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class WorldEventHandler
{
    public static boolean hasInitialized = false;

    @SubscribeEvent
    public void onWorldLoadEvent(WorldEvent.Load event)
    {
        if(!hasInitialized && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
        {
            MapStorage mapStorage = event.getWorld().getMapStorage();

//            RecipeRegistry.INSTANCE.registerVanillaRecipes();

            //long startTime = System.nanoTime();
            hasInitialized = true;
        }
    }
}
