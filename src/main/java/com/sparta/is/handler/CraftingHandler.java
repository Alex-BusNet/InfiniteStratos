package com.sparta.is.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CraftingHandler
{
    public static void init()
    {
        //Add Recipes here
        //CraftingManager.getInstance().getRecipeList().add(new <<RECIPE CLASS>>)
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
    {
        if(event.crafting.getItem() instanceof IOwnable)
        {
            ItemStackUtils.setOwner(event.crafting, event.player);
        }
    }
}
