package com.sparta.is.reference;

import com.sparta.is.utils.LogHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class Files
{
    private static File globalDataDir;
    public static File globalTestDir;
    public static File playerDataDir;

    public static void init(FMLPreInitializationEvent event)
    {
        globalDataDir = new File(event.getModConfigurationDirectory().getParentFile(), "data" + File.separator + Reference.MOD_ID);
        globalTestDir = new File(globalDataDir, "tests");
        globalTestDir.mkdirs();
        LogHelper.info(globalDataDir.toString());
    }

    public static void updateFileReferences()
    {
        playerDataDir = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "playerdata" + File.separator + Reference.MOD_ID);
        playerDataDir.mkdirs();
        LogHelper.info(playerDataDir.toString());
    }

}
