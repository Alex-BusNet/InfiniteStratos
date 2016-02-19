package com.sparta.is.client.util;

import com.sparta.is.utility.ResourceLocationHelper;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.audio.PositionedSoundRecord;

public class ClientSoundHelper
{
    public static void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
    {
        FMLClientHandler.instance().getClient().getSoundHandler().playSound(new PositionedSoundRecord(ResourceLocationHelper.getResourceLocation(soundName), volume, pitch, xCoord, yCoord, zCoord));
    }
}
