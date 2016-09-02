package com.sparta.is.proxy;

import com.sparta.is.handler.ConfigurationHandler;
import com.sparta.is.handler.CraftingHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements  IProxy
{
    @Override
    public void registerEventHandlers()
    {
        CraftingHandler craftingHandler = new CraftingHandler();

        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(craftingHandler);
    }

    public void registerItemRenderer(Item item, int meta, String id)
    {

    }

}
