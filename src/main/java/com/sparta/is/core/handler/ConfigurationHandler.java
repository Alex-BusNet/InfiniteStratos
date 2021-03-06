package com.sparta.is.core.handler;

import com.sparta.is.core.reference.Reference;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;

    private static final String CATEGORY_SERVER = "general.server";
    private static final String CATEGORY_ENERGY_VALUE = "general.energy_value";

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

        Settings.requireShiftToDisplayExtra = configuration.getBoolean(
                Settings.SHIFT_DISPLAYS_ENERGY_VALUE_NAME,
                CATEGORY_ENERGY_VALUE,
                Settings.SHIFT_DISPLAYS_ENERGY_VALUE_DEFAULT,
                I18n.translateToLocal(Settings.SHIFT_DISPLAYS_ENERGY_VALUE_COMMENT),
                Settings.SHIFT_DISPLAYS_ENERGY_VALUE_LABEL);

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

        public static boolean requireShiftToDisplayExtra;
        private static final String SHIFT_DISPLAYS_ENERGY_VALUE_NAME = "hold_shift_to_display_emc_value";
        private static final String SHIFT_DISPLAYS_ENERGY_VALUE_LABEL = "energy_value.hold_shift_to_display_emc_value.label";
        private static final String SHIFT_DISPLAYS_ENERGY_VALUE_COMMENT = "energy_value.hold_shift_to_display_emc_value.comment";
        private static final boolean SHIFT_DISPLAYS_ENERGY_VALUE_DEFAULT = true;
    }
}
