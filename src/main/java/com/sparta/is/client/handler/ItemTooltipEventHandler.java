package com.sparta.is.client.handler;

import com.sparta.is.inventory.ContainerISUnitStation;
import com.sparta.is.reference.Messages;
import com.sparta.is.utility.IOwnable;
import com.sparta.is.utility.ItemHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.text.DecimalFormat;
import java.util.UUID;

@SideOnly(Side.CLIENT)
public class ItemTooltipEventHandler
{
    private static DecimalFormat energyValueDecimalFormat = new DecimalFormat("###,###,###,###,###.###");

    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event)
    {
        if (((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) && (event.entityPlayer != null && event.entityPlayer.openContainer instanceof ContainerISUnitStation)))
        {
            //Add Tooltip info for Slots
        }

        if (event.itemStack.getItem() instanceof IOwnable)
        {
            UUID playerUUID = ItemHelper.getOwnerUUID(event.itemStack);
            if (playerUUID != null && UsernameCache.containsUUID(playerUUID))
            {
                event.toolTip.add(StatCollector.translateToLocalFormatted(Messages.Tooltips.ITEM_BELONGS_TO, UsernameCache.getLastKnownUsername(playerUUID)));
            }
            else if (ItemHelper.hasOwnerName(event.itemStack))
            {
                event.toolTip.add(StatCollector.translateToLocalFormatted(Messages.Tooltips.ITEM_BELONGS_TO, ItemHelper.getOwnerName(event.itemStack)));
            }
            else
            {
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
                {
                    event.toolTip.add(StatCollector.translateToLocal(Messages.Tooltips.ITEM_BELONGS_TO_NO_ONE));
                }
            }
        }
    }
}
