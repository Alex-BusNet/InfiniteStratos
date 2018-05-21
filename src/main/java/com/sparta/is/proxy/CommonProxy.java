package com.sparta.is.proxy;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.command.CommandIS;
import com.sparta.is.handler.*;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.init.ModEntities;
import com.sparta.is.init.ModItems;
import com.sparta.is.network.Network;
import com.sparta.is.reference.Files;
import com.sparta.is.utils.BlockUtils;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements  IProxy
{
    @Override
    public void onPreInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        Files.init(event);
        Network.init();
        ModItems.getItems().forEach(GameRegistry::register);
        GameRegistry.register(ModItems.tabaneSpawnEgg);
        ModItems.getMeleeItems().forEach(GameRegistry::register);
        ModItems.getUnits().forEach(GameRegistry::register);
        ModEntities.init();

        for ( Block block : ModBlocks.getBlocks() )
        {
            GameRegistry.register(block);
            GameRegistry.register(BlockUtils.getItemBlockFor(block), block.getRegistryName());
        }

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

//        CraftingHandler.init();

        GameRegistry.registerFuelHandler(new FuelHandler());
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

    public void registerEventHandlers()
    {
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
