package com.sparta.is.core.handler;

import com.sparta.is.core.utils.interfaces.IOwnable;
import com.sparta.is.core.utils.ItemStackUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CraftingHandler
{
    public static void init()
    {
        //Add Recipes here

    }

//    @SubscribeEvent
//    public void

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
    {
        if(event.crafting.getItem() instanceof IOwnable )
        {
            ItemStackUtils.setOwner(event.crafting, event.player);
        }
    }
}
