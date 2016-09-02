package com.sparta.is.proxy;

import com.sparta.is.client.handler.KeyInputEventHandler;
import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public ClientProxy getClientProxy()
    {
        return this;
    }

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.STANDBY);
        ClientRegistry.registerKeyBinding(KeyBindings.PARTIAL_DEPLOY);
        ClientRegistry.registerKeyBinding(KeyBindings.FULL_DEPLOY);
        ClientRegistry.registerKeyBinding(KeyBindings.ONE_OFF_OFF);
        ClientRegistry.registerKeyBinding(KeyBindings.ONE_OFF_ON);
        ClientRegistry.registerKeyBinding(KeyBindings.EQUALIZER_ACCESS);
    }

    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + id, "inventory"));
    }
}
