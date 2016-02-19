package com.sparta.is.proxy;

import com.sparta.is.client.gui.overlay.UnitOverlay;
import com.sparta.is.client.handler.ArmorEventHandler;
import com.sparta.is.handler.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public abstract class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        ItemEventHandler itemEventHandler = new ItemEventHandler();
        CraftingHandler craftingHandler = new CraftingHandler();
        PlayerEventHandler playerEventHandler = new PlayerEventHandler();
        ArmorEventHandler armorEventHandler = new ArmorEventHandler();

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        FMLCommonHandler.instance().bus().register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        FMLCommonHandler.instance().bus().register(playerEventHandler);
        FMLCommonHandler.instance().bus().register(craftingHandler);
        MinecraftForge.EVENT_BUS.register(craftingHandler);
        MinecraftForge.EVENT_BUS.register(new UnitOverlay(Minecraft.getMinecraft()));
        MinecraftForge.EVENT_BUS.register(armorEventHandler);
    }

    public ModelBiped getArmorModel(int id)
    {
        return null;
    }
}
