package com.sparta.is;

import com.sparta.is.proxy.IProxy;
import com.sparta.is.reference.Messages;
import com.sparta.is.reference.Reference;
import com.sparta.is.utils.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = Reference.MOD_ID,
        name = "InfiniteStratos",
        certificateFingerprint = Reference.FINGERPRINT,
        version = "@MOD_VERSION@",
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

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

}
