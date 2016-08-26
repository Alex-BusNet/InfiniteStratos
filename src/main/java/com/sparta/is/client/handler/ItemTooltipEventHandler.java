package com.sparta.is.client.handler;

import com.sparta.is.inventory.ContainerISUnitStation;
import com.sparta.is.reference.Messages;
import com.sparta.is.utility.IOwnable;
import com.sparta.is.utility.ItemHelper;
import net.minecraft.client.resources.I18n;
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
        if (((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) && (event.getEntityPlayer() != null && event.getEntityPlayer().openContainer instanceof ContainerISUnitStation)))
        {
            //Add Tooltip info for Slots
        }

        if (event.getItemStack().getItem() instanceof IOwnable)
        {
            UUID playerUUID = ItemHelper.getOwnerUUID(event.getItemStack());
            if (playerUUID != null && UsernameCache.containsUUID(playerUUID))
            {
                event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO, UsernameCache.getLastKnownUsername(playerUUID)));
            }
            else if (ItemHelper.hasOwnerName(event.getItemStack()))
            {
                event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO, ItemHelper.getOwnerName(event.getItemStack())));
            }
            else
            {
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
                {
                    event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO_NO_ONE));
                }
            }
        }
    }
}
