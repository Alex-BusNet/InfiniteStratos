package com.sparta.is.proxy;

import com.sparta.is.core.InfiniteStratos;
import com.sparta.is.core.command.CommandIS;
import com.sparta.is.core.handler.*;
import com.sparta.is.core.network.Network;
import com.sparta.is.core.network.PacketHandler;
import com.sparta.is.core.reference.Files;
import com.sparta.is.core.tileentity.TileEntityIS;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.init.ModFluids;
import com.sparta.is.init.ModItems;
import com.sparta.is.init.ModPlugins;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public abstract class CommonProxy implements IProxy
{
    @Override
    public void onPreInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        Files.init(event);
        Network.init();
        PacketHandler.init();

        ModBlocks.preInit();
        ModItems.preInit();
        ModFluids.preInit();
//        ModPlugins.preInit();

        registerEventHandlers();

        InfiniteStratos.commonCapsLoaded = Loader.isModLoaded("commoncapabilities");
    }

    @Override
    public void onInit(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(InfiniteStratos.instance, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        MinecraftForge.EVENT_BUS.register(new CraftingHandler());

        ModItems.init();
        TileEntityIS.init();

//        CraftingHandler.init();

//        GameRegistry.registerFuelHandler(new FuelHandler());
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event)
    {

    }

    @Override
    public void onServerStarting(FMLServerStartingEvent event)
    {
        Files.updateFileReferences();
        event.registerServerCommand(new CommandIS());
    }

    @Override
    public void onServerStopping(FMLServerStoppingEvent event)
    {
        WorldEventHandler.hasInitialized = false;
    }

    @Mod.EventHandler
    public void handleIdMappingEvent(FMLModIdMappingEvent event)
    {
        ModFluids.refreshReferences();
    }

    @Mod.EventHandler
    public void handleIMC(FMLInterModComms.IMCEvent event)
    {
        ModPlugins.initialize();
    }

    public void registerEventHandlers()
    {
        LogHelper.info("Registering event handlers...");
        ItemEventHandler itemEventHandler = new ItemEventHandler();
        CraftingHandler craftingHandler = new CraftingHandler();
        PlayerEventHandler playerEventHandler = new PlayerEventHandler();

        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        MinecraftForge.EVENT_BUS.register(craftingHandler);
        MinecraftForge.EVENT_BUS.register(craftingHandler);
    }

}
