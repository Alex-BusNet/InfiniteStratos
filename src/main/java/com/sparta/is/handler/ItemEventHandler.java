package com.sparta.is.handler;

import com.sparta.is.utils.NBTUtils;
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
        NBTUtils.clearStatefulNBTTags(itemTossEvent.getEntityItem().getItem());
    }

    @SubscribeEvent
    public void onItemPickupEvent(PlayerEvent.ItemPickupEvent itemPickupEvent)
    {
        NBTUtils.clearStatefulNBTTags(itemPickupEvent.getOriginalEntity().getItem());
    }

    @SubscribeEvent
    public void onItemPickupEvent(EntityItemPickupEvent entityItemPickupEvent)
    {
        NBTUtils.clearStatefulNBTTags(entityItemPickupEvent.getItem().getItem());
    }

    @SubscribeEvent
    public void onPlayerDropEvent(PlayerDropsEvent playerDropsEvent)
    {
        for(EntityItem entityItem : playerDropsEvent.getDrops())
        {
            NBTUtils.clearStatefulNBTTags(entityItem.getItem());
        }
    }



}
