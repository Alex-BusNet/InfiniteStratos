package com.sparta.is.core.integration;

import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.Loader;

public class PluginISBase extends com.sparta.is.core.utils.PluginCore
{
    public PluginISBase(String modId, String modName)
    {
        super(modId, modName);
    }

    @Override
    public boolean register() {

        String category = "Plugins";
        String comment = "If TRUE, support for " + modName + " is enabled.";
        enable = Loader.isModLoaded(modId);

        if (!enable) {
            return false;
        }
        initializeDelegate();
        return !error;
    }

    public boolean initialize() {

        if (!enable) {
            return false;
        }
        try {
            registerDelegate();
        } catch (Throwable t) {
            LogHelper.error(modName + " Plugin encountered an error:", t);
            error = true;
        }
        if (!error) {
            LogHelper.info(modName + " Plugin Enabled.");
        }
        return !error;
    }

    public void initializeDelegate() {

    }

    public void registerDelegate() {

    }

    /* HELPERS */
    public PotionType getPotionType(String baseName, String qualifier) {

        if (qualifier.isEmpty()) {
            return PotionType.getPotionTypeForName(modId + ":" + baseName);
        }
        PotionType ret = PotionType.getPotionTypeForName(modId + ":" + baseName + "_" + qualifier);

        if (ret == PotionTypes.EMPTY) {
            ret = PotionType.getPotionTypeForName(modId + ":" + qualifier + "_" + baseName);
        }
        return ret;
    }
}
