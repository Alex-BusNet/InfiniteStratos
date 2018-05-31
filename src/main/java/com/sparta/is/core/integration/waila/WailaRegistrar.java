package com.sparta.is.core.integration.waila;

import com.sparta.is.core.armor.ArmorIS;
import mcp.mobius.waila.api.IWailaRegistrar;
import slimeknights.tconstruct.library.Util;

public class WailaRegistrar
{
    public static final String CONFIG_OWNER = Util.prefix("owner");
    public static final String CONFIG_STATE = Util.prefix("state");

    public static void wailaCallback(IWailaRegistrar registrar)
    {
        registrar.addConfig("Infinite Stratos", CONFIG_OWNER, true);
        registrar.addConfig("Infinite Stratos", CONFIG_STATE, true);

        UnitDataProvider udp = new UnitDataProvider();
        registrar.registerBodyProvider(udp, ArmorIS.class);
    }
}
