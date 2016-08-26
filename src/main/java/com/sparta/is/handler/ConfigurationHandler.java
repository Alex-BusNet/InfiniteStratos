package com.sparta.is.handler;

import com.sparta.is.reference.Reference;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;

    private static final String CATEGORY_SERVER = "general.server";

    public static void init(File configFile)
    {
        if ( configuration == null )
        {
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        Settings.serverSyncThreshold = configuration.getInt(
                Settings.SERVER_SYNC_THRESHOLD_NAME,
                CATEGORY_SERVER,
                Settings.SERVER_SYNC_THRESHOLD_DEFAULT,
                Settings.SERVER_SYNC_THRESHOLD_MIN,
                Settings.SERVER_SYNC_THRESHOLD_MAX,
                I18n.translateToLocal(Settings.SERVER_SYNC_THRESHOLD_COMMENT),
                Settings.SERVER_SYNC_THRESHOLD_LABEL);

        if ( configuration.hasChanged() )
            configuration.save();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if ( event.getModID().equalsIgnoreCase(Reference.MOD_ID) )
        {
            loadConfiguration();
        }
    }

    public static class Settings
    {

        public static int serverSyncThreshold;
        private static final String SERVER_SYNC_THRESHOLD_NAME = "sync_threshold";
        private static final String SERVER_SYNC_THRESHOLD_LABEL = "server.sync_threshold.label";
        private static final String SERVER_SYNC_THRESHOLD_COMMENT = "server.sync_threshold.comment";
        private static final int SERVER_SYNC_THRESHOLD_DEFAULT = 5;
        private static final int SERVER_SYNC_THRESHOLD_MIN = 0;
        private static final int SERVER_SYNC_THRESHOLD_MAX = Short.MAX_VALUE;
    }
}
