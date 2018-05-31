package com.sparta.is.client.handler;

import com.sparta.is.core.exchange.WrappedStack;
import com.sparta.is.core.handler.ConfigurationHandler;
import com.sparta.is.core.reference.Colors;
import com.sparta.is.core.reference.Messages;
import com.sparta.is.core.utils.interfaces.IOwnable;
import com.sparta.is.core.utils.ItemStackUtils;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ItemToolTipEventHandler
{
    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (shouldBeDisplayed()) {

            WrappedStack wrappedItemStack = WrappedStack.build(event.getItemStack());

            event.getToolTip().add(String.format("%s%s", Colors.TextColor.YELLOW, I18n.format(Messages.ToolTips.NO_ENERGY_VALUE)));

            if (event.getItemStack().getItem() instanceof IOwnable ) {
                String playerName = ItemStackUtils.getOwnerName(event.getItemStack());
                if (playerName != null) {
                    event.getToolTip().add(I18n.format(Messages.ToolTips.ITEM_BELONGS_TO, playerName));
                }
                else {
                    event.getToolTip().add(I18n.format(Messages.ToolTips.ITEM_BELONGS_TO_NO_ONE));
                }
            }
        }
    }

    /**
     * TODO Documentation
     *
     * @return
     */
    private static boolean shouldBeDisplayed() {
        return !ConfigurationHandler.Settings.requireShiftToDisplayExtra || (ConfigurationHandler.Settings.requireShiftToDisplayExtra && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)));
    }

}
