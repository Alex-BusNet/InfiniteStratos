package com.sparta.is;

import com.sparta.is.command.CommandIS;
import com.sparta.is.handler.ConfigurationHandler;
import com.sparta.is.handler.FuelHandler;
import com.sparta.is.handler.GuiHandler;
import com.sparta.is.init.ModItems;
import com.sparta.is.network.Network;
import com.sparta.is.proxy.IProxy;
import com.sparta.is.recipe.RecipeRegistry;
import com.sparta.is.reference.Messages;
import com.sparta.is.reference.Reference;
import com.sparta.is.utils.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID,
        name = "InfiniteStratos",
        certificateFingerprint = Reference.FINGERPRINT,
        version = Reference.MOD_VERSION,
        dependencies = "required-after:Forge@[12.18.1,)",
        guiFactory = "com.sparta.is.client.gui.GuiFactory")

public class InfiniteStratos
{
    @Mod.Instance(Reference.MOD_ID)
    public static InfiniteStratos instance;

    @SidedProxy(clientSide = "com.sparta.is.proxy.ClientProxy", serverSide = "com.sparta.is.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
    {
        if( Reference.FINGERPRINT.equals("@FINGERPRINT@"))
        {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        }
        else
        {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event)
    {
//        Files.updateFileReferences();
        event.registerServerCommand(new CommandIS());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        Network.init();

        proxy.registerKeyBindings();

        ModItems.register();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        proxy.registerEventHandlers();

        GameRegistry.registerFuelHandler(new FuelHandler());

        FMLInterModComms.sendMessage("Waila", "register", "com.sparta.is.waila.WailaDataProvider.callbackRegister");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    public RecipeRegistry getRecipeRegistry()
    {
        return RecipeRegistry.INSTANCE;
    }


}
