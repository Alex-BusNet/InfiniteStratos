package com.sparta.is.handler;

import com.sparta.is.client.gui.overlay.UnitOverlay;
import com.sparta.is.init.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class OverlayEventHandler
{
    private UnitOverlay unitOverlay;

    public OverlayEventHandler(UnitOverlay unitOverlay)
    {
        this.unitOverlay = unitOverlay;
    }

    @SubscribeEvent(receiveCanceled=true)
    public void onEvent(RenderGameOverlayEvent.Pre event)
    {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().thePlayer;
        if (entityPlayerSP == null) return;  // just in case

        // look for the ItemHUDactivator in the hotbar.  If not present, return without changing the HUD.
        boolean foundInHotbar = false;
        final int LAST_ARMOR_SLOT_PLUS_ONE = 0 + entityPlayerSP.inventory.getHotbarSize();

        for (int i = 0; i < LAST_ARMOR_SLOT_PLUS_ONE; ++i)
        {
            ItemStack slotItemStack = entityPlayerSP.inventory.armorItemInSlot(i);

            if (slotItemStack != null && slotItemStack.getItem() == ModItems.yukihiraNigata)
            {
                foundInHotbar = true;
                break;
            }
        }
        if (!foundInHotbar) return;

        switch (event.type)
        {
            case HEALTH:
                unitOverlay.renderStatusBar(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
                event.setCanceled(true);
                break;
            case ARMOR:
                event.setCanceled(true);
                break;
            case HOTBAR:
                GL11.glColor3f(1, 0.7f, 0);
                break;
            default:
                break;
        }
    }

    @SubscribeEvent(receiveCanceled=true)
    public void onEvent(RenderGameOverlayEvent.Post event) {

        switch (event.type) {
            case HEALTH:
                break;
            case HOTBAR:
                GL11.glColor3f(1, 1, 1);
                break;
            default:
                break;
        }
    }
}
