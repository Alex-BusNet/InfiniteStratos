package com.sparta.is.proxy;

import com.sparta.is.armor.ArmorIS;
import com.sparta.is.armor.UnitByakushiki;
import com.sparta.is.armor.units.Byakushiki;
import com.sparta.is.client.handler.ItemToolTipEventHandler;
import com.sparta.is.client.handler.KeyInputEventHandler;
import com.sparta.is.client.render.entity.EntityRendererTabane;
import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.entity.EntityTabane;
import com.sparta.is.reference.EnumUnitState;
import com.sparta.is.settings.OneOffSettings;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.utils.LogHelper;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy extends CommonProxy
{
    public OneOffSettings oneOffSettings = new OneOffSettings();
    public UnitSettings unitSettings = new UnitSettings();

    private static Byakushiki byakushikiModel = new Byakushiki(1.0f, false);
    private static Byakushiki byakushikiPartialModel = new Byakushiki(1.0f, true);

    @Override
    public ClientProxy getClientProxy()
    {
        return this;
    }

    @Override
    public void onServerStarting(FMLServerStartingEvent event)
    {
        super.onServerStarting(event);
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event)
    {
        LogHelper.info("Pre-initializing...");
        super.onPreInit(event);

//        OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);

        RenderingRegistry.registerEntityRenderingHandler(EntityTabane.class, EntityRendererTabane::new);


        ClientRegistry.registerKeyBinding(KeyBindings.STANDBY);
        ClientRegistry.registerKeyBinding(KeyBindings.PARTIAL_DEPLOY);
        ClientRegistry.registerKeyBinding(KeyBindings.FULL_DEPLOY);
        ClientRegistry.registerKeyBinding(KeyBindings.ONE_OFF);
        ClientRegistry.registerKeyBinding(KeyBindings.EQUALIZER_ACCESS);
    }

    @Override
    public void onInit(FMLInitializationEvent event)
    {
        LogHelper.info("Initializing...");
        super.onInit(event);

        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
        MinecraftForge.EVENT_BUS.register(new ItemToolTipEventHandler());
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("Post-initializing...");
        super.onPostInit(event);
    }

    @Override
    public void onServerStopping(FMLServerStoppingEvent event)
    {
        super.onServerStopping(event);
    }

    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
    }

    public ModelBiped getArmorModel(ArmorIS armor)
    {
        if(armor instanceof UnitByakushiki)
        {
            if(armor.getState() == EnumUnitState.FULL_DEPLOY_STATE)
                return byakushikiModel;
            else if (armor.getState() == EnumUnitState.PARTIAL_DEPLOY_STATE)
                return byakushikiPartialModel;
        }

        return null;
    }
}
