package com.sparta.is.handler;

import com.sparta.is.item.crafting.RecipeGenericUnitDyes;
import com.sparta.is.utils.IOwnable;
import com.sparta.is.utils.ItemStackUtils;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CraftingHandler
{
    public static void init()
    {
        //Add Recipes here
        CraftingManager.getInstance().getRecipeList().add(new RecipeGenericUnitDyes());
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
    {
        if(event.crafting.getItem() instanceof IOwnable )
        {
            ItemStackUtils.setOwner(event.crafting, event.player);
        }
    }
}
