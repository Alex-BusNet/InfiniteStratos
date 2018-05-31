package com.sparta.is.core.integration.waila;

import com.sparta.is.core.armor.ArmorIS;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;

import java.util.List;

public class UnitDataProvider implements IWailaDataProvider
{
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler handler)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler handler)
    {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler handler)
    {
        if(handler.getConfig(WailaRegistrar.CONFIG_STATE) && (accessor.getStack().getItem() instanceof ArmorIS) )
        {
            ArmorIS ais = (ArmorIS)accessor.getStack().getItem();
            currentTip.add("Unit: " + ais.getBaseTagName() + "\nCurrent State: " + ais.getState().getName());
        }
        else if(handler.getConfig(WailaRegistrar.CONFIG_OWNER) && (accessor.getStack().getItem() instanceof ArmorIS))
        {
            ArmorIS ais = (ArmorIS)accessor.getStack().getItem();
            currentTip.add("Unit: " + ais.getBaseTagName() + "\nBelongs to: " + ais.getState().getName());
        }

        return currentTip;
    }
}
