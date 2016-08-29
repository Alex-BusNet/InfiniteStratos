package com.sparta.is.handler;

import com.sparta.is.item.crafting.RecipeGenericUnitDyes;
import net.minecraft.item.crafting.CraftingManager;

public class CraftingHandler
{
    public static void init()
    {
        //Add Recipes here
        CraftingManager.getInstance().getRecipeList().add(new RecipeGenericUnitDyes());
    }

//    @SubscribeEvent
//    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
//    {
//        if(event.crafting.getItem() instanceof IOwnable )
//        {
//            ItemStackUtils.setOwner(event.crafting, event.player);
//        }
//    }
}
