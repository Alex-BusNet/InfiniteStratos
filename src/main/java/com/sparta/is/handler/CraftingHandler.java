package com.sparta.is.handler;

import com.sparta.is.utility.IOwnable;
import com.sparta.is.utility.ItemHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

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
