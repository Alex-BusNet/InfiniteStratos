package com.sparta.is.handler;

import com.sparta.is.utility.IOwnable;
import com.sparta.is.utility.ItemHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CraftingHandler
{
    public static void init()
    {
        //Change color of Units?
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
    {
        if(event.crafting.getItem() instanceof IOwnable)
        {
            ItemHelper.setOwner(event.crafting, event.player);
        }
    }

}
