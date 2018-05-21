package com.sparta.is.network;

import com.sparta.is.network.message.MessageKeyPressed;
import com.sparta.is.network.message.MessageOneOffSettings;
import com.sparta.is.network.message.MessageUnitSettings;
import com.sparta.is.reference.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class Network
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static void init()
    {
        INSTANCE.registerMessage(MessageKeyPressed.MessageHandler.class, MessageKeyPressed.class, 4, Side.SERVER);
        INSTANCE.registerMessage(MessageOneOffSettings.MessageHandler.class, MessageOneOffSettings.class, 5, Side.SERVER);
        INSTANCE.registerMessage(MessageUnitSettings.MessageHandler.class, MessageUnitSettings.class, 6, Side.SERVER);
    }

}
