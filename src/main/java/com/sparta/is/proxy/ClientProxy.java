package com.sparta.is.proxy;

import com.sparta.is.armor.ArmorIS;
import com.sparta.is.block.BlockIS;
import com.sparta.is.client.handler.ItemToolTipEventHandler;
import com.sparta.is.client.handler.KeyInputEventHandler;
import com.sparta.is.client.render.entity.EntityRendererTabane;
import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.entity.EntityTabane;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.init.ModItems;
import com.sparta.is.item.base.ItemIS;
import com.sparta.is.item.base.ItemISMelee;
import com.sparta.is.reference.Reference;
import com.sparta.is.armor.models.ModelUnit;
import com.sparta.is.settings.OneOffSettings;
import com.sparta.is.settings.UnitSettings;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    public OneOffSettings oneOffSettings = new OneOffSettings();
    public UnitSettings unitSettings = new UnitSettings();

    @Override
    public ClientProxy getClientProxy()
    {
        return this;
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event)
    {
        super.onPreInit(event);
        ModItems.getItems().forEach(ItemIS::initModelsAndVariants);
        ModItems.getMeleeItems().forEach(ItemISMelee::initModelsAndVariants);
        ModItems.getUnits().forEach(ArmorIS::initModelsAndVariants);
        ModBlocks.getBlocks().forEach(BlockIS::initModelsAndVariants);

        OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);

        RenderingRegistry.registerEntityRenderingHandler(EntityTabane.class, EntityRendererTabane::new);
        ModelLoader.setCustomModelResourceLocation(ModItems.tabaneSpawnEgg, 0, new ModelResourceLocation(ModItems.tabaneSpawnEgg.getRegistryName().toString()));

        ClientRegistry.registerKeyBinding(KeyBindings.STANDBY);
        ClientRegistry.registerKeyBinding(KeyBindings.PARTIAL_DEPLOY);
        ClientRegistry.registerKeyBinding(KeyBindings.FULL_DEPLOY);
        ClientRegistry.registerKeyBinding(KeyBindings.ONE_OFF);
        ClientRegistry.registerKeyBinding(KeyBindings.EQUALIZER_ACCESS);
    }

    @Override
    public void onInit(FMLInitializationEvent event)
    {
        super.onInit(event);

        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
        MinecraftForge.EVENT_BUS.register(new ItemToolTipEventHandler());

        ModelUnit unit = new ModelUnit(1.0f, 0.0f, 64, 64, "byakushiki_partial_deploy_new");
    }
}
