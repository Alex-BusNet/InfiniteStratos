package com.sparta.is.init;

import cofh.core.util.core.IInitializer;
import com.sparta.is.core.integration.tcon.TConstruct;

import java.util.ArrayList;

public class ModPlugins
{
    private ModPlugins() {}

    public static void preInit()
    {
        pluginTCon = new TConstruct();

        initList.add(pluginTCon);

        for(IInitializer init : initList)
        {
            init.register();
        }
    }

    public static void initialize()
    {
        for(IInitializer init : initList)
        {
            init.initialize();
        }
    }

    private static ArrayList<IInitializer> initList = new ArrayList<>();

    private static TConstruct pluginTCon;
}
