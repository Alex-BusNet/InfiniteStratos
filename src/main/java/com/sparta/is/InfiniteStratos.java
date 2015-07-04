package com.sparta.is;

import com.sparta.is.api.array.AlchemyArrayRegistry;
import com.sparta.is.client.handler.KeyInputEventHandler;
import com.sparta.is.handler.ConfigurationHandler;
import com.sparta.is.handler.CraftingHandler;
import com.sparta.is.handler.GuiHandler;
import com.sparta.is.init.*;
import com.sparta.is.network.PacketHandler;
import com.sparta.is.proxy.IProxy;
import com.sparta.is.reference.Messages;
import com.sparta.is.reference.Reference;
import com.sparta.is.utility.LogHelper;
import com.sparta.is.utility.TileEntityDataHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, certificateFingerprint = Reference.FINGERPRINT, version = Reference.MOD_VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class InfiniteStratos
{
    @Mod.Instance(Reference.MOD_ID)
    public static InfiniteStratos instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void  invalidFingerprint(FMLFingerprintViolationEvent event)
    {
        if(Reference.FINGERPRINT.equals("@FINGERPRINT@"))
        {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        }
        else
        {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        PacketHandler.init();

        proxy.registerKeyBindings();

        ModItems.init();

        ModBlocks.init();

        ModMaterials.init();

        ModUnits.init();

        ModEntities.init();

        LogHelper.info("Pre-Initialization Complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Registers the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        //Initialize the mod tile entities
        TileEntities.init();

        //Initialize Custom rendering and pre-load textures (Client only)
        proxy.initRenderingAndTextures();

        proxy.registerEventHandlers();

        CraftingHandler.init();
        Recipes.init();

        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        FMLInterModComms.sendMessage("Waila", "register", "com.sparta.is.waila.WailaDataProvider.callBackRegister");

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

    public AlchemyArrayRegistry getAlchemyArrayRegistry()
    {
        return AlchemyArrayRegistry.getInstance();
    }

    public TileEntityDataHelper getTileEntityDataHelper()
    {
        return TileEntityDataHelper.getInstance();
    }
}