package com.sparta.is.core;

import com.sparta.is.core.client.model.ISUnitModelLoader;
import com.sparta.is.core.reference.Messages;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.entity.driveables.types.IScope;
import com.sparta.is.proxy.IProxy;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@Mod(modid = Reference.MOD_ID,
    name = "Infinite Stratos",
    certificateFingerprint = Reference.FINGERPRINT,
    version = Reference.MOD_VERSION,
    guiFactory = "com.sparta.is.client.gui.GuiFactory")

public class InfiniteStratos
{
    @Mod.Instance(Reference.MOD_ID)
    public static InfiniteStratos instance;

    @SidedProxy(clientSide = "com.sparta.is.proxy.ClientProxy", serverSide = "com.sparta.is.proxy.ServerProxy")
    public static IProxy proxy;

    public static boolean commonCapsLoaded;

    private static ISUnitModelLoader unitModelLoader = new ISUnitModelLoader();

    @SideOnly(Side.CLIENT)
    public static void registerLoaders()
    {
        ModelLoaderRegistry.registerLoader(unitModelLoader);

    }

    @Mod.EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
    {
        if(Reference.FINGERPRINT.equals("@FINGERPRINT@"))
        {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        }
        else
        {
            LogHelper.info(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        if(event.getSide() == Side.CLIENT)
            registerLoaders();

        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) { proxy.onInit(event); }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) { proxy.onPostInit(event); }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) { proxy.onServerStarting(event); }

    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) { proxy.onServerStopping(event); }


    public IScope currentScope = null;

}
