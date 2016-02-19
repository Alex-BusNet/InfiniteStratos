package com.sparta.is.network;

import com.sparta.is.network.message.*;
import com.sparta.is.reference.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.LOWERCASE_MOD_ID);

    public static void init()
    {
        INSTANCE.registerMessage(MessageTileEntityIS.class, MessageTileEntityIS.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityDummy.class, MessageTileEntityDummy.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityAlchemyArray.class, MessageTileEntityAlchemyArray.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(MessageSoundEvent.class, MessageSoundEvent.class, 3, Side.CLIENT);
        INSTANCE.registerMessage(MessageChalkSettings.class, MessageChalkSettings.class, 4, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityISUnitStation.class, MessageTileEntityISUnitStation.class, 5, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityUnitStand.class, MessageTileEntityUnitStand.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(MessageEntityTabane.class, MessageEntityTabane.class, 7, Side.CLIENT);
        INSTANCE.registerMessage(MessageEntityIS.class, MessageEntityIS.class, 8, Side.CLIENT);
        INSTANCE.registerMessage(MessageUnitByakushiki.class, MessageUnitByakushiki.class, 9, Side.CLIENT);
        INSTANCE.registerMessage(MessageKeyPressed.class, MessageKeyPressed.class, 10, Side.SERVER);
        INSTANCE.registerMessage(MessageUnitSettings.class, MessageUnitSettings.class, 11, Side.CLIENT);
        INSTANCE.registerMessage(MessageOneOffSettings.class, MessageOneOffSettings.class, 12, Side.CLIENT);

    }

}
