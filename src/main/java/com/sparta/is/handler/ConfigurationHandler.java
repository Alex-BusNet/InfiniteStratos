package com.sparta.is.handler;

import com.sparta.is.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;

    public static void init(File configFile)
    {
        if(configuration == null)
        {
            configuration = new Configuration(configFile);
        }

        loadConfiguration();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent event)
    {
        if(event.getModID().equalsIgnoreCase(Reference.MOD_ID))
        {
            // Resync configs
        }
    }

    private static void loadConfiguration()
    {
//        Settings.General.syncThreshold = configuration.getInt(Messages.Configuration.GENERAL_SYNC_THRESHOLD, Configuration.CATEGORY_GENERAL, 5, 0, Short.MAX_VALUE, StatCollector.translateToLocal(Messages.Configuration.GENERAL_SYNC_THRESHOLD_COMMENT), Messages.Configuration.GENERAL_SYNC_THRESHOLD_LABEL);

//        Settings.Sounds.soundMode = ConfigurationHelper.getString(configuration, Messages.Configuration.SOUND_MODE, Configuration.CATEGORY_GENERAL, "All", StatCollector.translateToLocal(Messages.Configuration.SOUND_MODE_COMMENT), new String[]{"All", "Self", "None"}, Messages.Configuration.SOUND_MODE_LABEL);


        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}