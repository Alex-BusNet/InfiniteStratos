package com.sparta.is;

import com.sparta.is.client.handler.ConfigurationHandler;
import com.sparta.is.client.handler.KeyInputHandler;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.init.ModItems;
import com.sparta.is.init.Recipes;
import com.sparta.is.proxy.IProxy;
import com.sparta.is.reference.Reference;
import com.sparta.is.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class InfiniteStratos
{
    @Mod.Instance(Reference.MOD_ID)
    public static InfiniteStratos instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
{
    ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

    proxy.registerKeyBindings();

    ModItems.init();

    ModBlocks.init();

    LogHelper.info("Pre-Initialization Complete");
}

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        Recipes.init();
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());

        LogHelper.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        for (String oreName : OreDictionary.getOreNames())
        {
            LogHelper.info(oreName);
        }

        LogHelper.info("Post Initialization Complete");
    }
}