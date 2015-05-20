package com.sparta.is.proxy;

import com.sparta.is.client.settings.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.standbyKey);
        ClientRegistry.registerKeyBinding(KeyBindings.fullDeployKey);
        ClientRegistry.registerKeyBinding(KeyBindings.partialDeployKey);
        ClientRegistry.registerKeyBinding(KeyBindings.charge);
        ClientRegistry.registerKeyBinding(KeyBindings.release);
    }
}
