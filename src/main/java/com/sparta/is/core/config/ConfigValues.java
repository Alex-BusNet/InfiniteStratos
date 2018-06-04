package com.sparta.is.core.config;

import com.sparta.is.core.config.values.ConfigBoolValues;
import com.sparta.is.core.config.values.ConfigIntListValues;
import com.sparta.is.core.config.values.ConfigIntValues;
import com.sparta.is.core.config.values.ConfigStringListValues;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

public final class ConfigValues
{
    public static Item itemRedstoneTorchConfigurator;
    public static Item itemCompassConfigurator;

    public static void defineConfigValues(Configuration config)
    {
        for(ConfigIntValues currConf : ConfigIntValues.values())
        {
            currConf.currentValue = config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc, currConf.min, currConf.max).getInt();
        }

        for(ConfigBoolValues currConf : ConfigBoolValues.values())
        {
            currConf.currentValue = config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc).getBoolean();
        }

        for(ConfigIntListValues currConf : ConfigIntListValues.values())
        {
            currConf.currentValue = config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc).getIntList();
        }

        for(ConfigStringListValues currConf : ConfigStringListValues.values())
        {
            currConf.currentValue = config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc).getStringList();
        }

        parseConfiguratorConfig();
    }

    private static void parseConfiguratorConfig()
    {
        itemRedstoneTorchConfigurator = null;
        itemCompassConfigurator = null;

        String[] conf = ConfigStringListValues.CONFIGURE_ITEMS.getValue();
        if(conf.length == 2)
        {
            itemRedstoneTorchConfigurator = Item.REGISTRY.getObject(new ResourceLocation(conf[0]));
            itemCompassConfigurator = Item.REGISTRY.getObject(new ResourceLocation(conf[1]));
        }

        if(itemRedstoneTorchConfigurator == null || itemCompassConfigurator == null)
        {
            LogHelper.error("Parsing the Configuration Items config failed, reverting back to the default settings!");

            itemRedstoneTorchConfigurator = Item.getItemFromBlock(Blocks.REDSTONE_TORCH);
            itemCompassConfigurator = Items.COMPASS;
        }
    }
}
