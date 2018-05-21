package com.sparta.is;

import com.sparta.is.entity.driveables.types.IScope;
import com.sparta.is.proxy.IProxy;
import com.sparta.is.recipe.RecipeRegistry;
import com.sparta.is.reference.Messages;
import com.sparta.is.reference.Reference;
import com.sparta.is.utils.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;


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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) { proxy.onPreInit(event); }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) { proxy.onInit(event); }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) { proxy.onPostInit(event); }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) { proxy.onServerStarting(event); }

    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) { proxy.onServerStopping(event); }


    public RecipeRegistry getRecipeRegistry() { return RecipeRegistry.INSTANCE; }

    public IScope currentScope = null;

}
