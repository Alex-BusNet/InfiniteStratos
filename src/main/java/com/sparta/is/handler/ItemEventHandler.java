package com.sparta.is.handler;

import com.sparta.is.utility.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class ItemEventHandler
{
    @SubscribeEvent
    public void onItemTossEvent(ItemTossEvent itemTossEvent)
    {
        NBTHelper.clearStatefulNBTTags(itemTossEvent.entityItem.getEntityItem());

        //NOOP
    }

    @SubscribeEvent
    public void onItemPickupEvent(PlayerEvent.ItemPickupEvent itemPickupEvent)
    {
        NBTHelper.clearStatefulNBTTags(itemPickupEvent.pickedUp.getEntityItem());
    }

    @SubscribeEvent
    public void onEntityItemPickupEvent(EntityItemPickupEvent entityItemPickupEvent)
    {
        NBTHelper.clearStatefulNBTTags(entityItemPickupEvent.item.getEntityItem());
    }

    @SubscribeEvent
    public void onPlayerDropsEvent(PlayerDropsEvent playerDropsEvent)
    {
        for (EntityItem entityItem : playerDropsEvent.drops)
        {
            NBTHelper.clearStatefulNBTTags(entityItem.getEntityItem());
        }
    }
}
