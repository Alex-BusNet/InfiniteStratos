package com.sparta.is.api.array;

import com.sparta.is.InfiniteStratos;
import cpw.mods.fml.common.Mod;

import java.util.SortedSet;

public class AlchemyArrayRegistryProxy
{
    @Mod.Instance("IS")
    private static Object isMod;

    public static boolean registerAlchemyArray(AlchemyArray alchemyArray)
    {
        init();

        if (isMod != null)
        {
            return ISWrapper.ismod.getAlchemyArrayRegistry().registerAlchemyArray(alchemyArray);
        }

        return false;
    }

    public static SortedSet<AlchemyArray> getRegisteredAlchemyArrays()
    {
        init();

        if (isMod != null)
        {
            return ISWrapper.ismod.getAlchemyArrayRegistry().getRegisteredAlchemyArrays();
        }

        return null;
    }

    private static class ISWrapper
    {
        private static InfiniteStratos ismod;
    }

    private static void init()
    {
        if (isMod != null)
        {
            ISWrapper.ismod = (InfiniteStratos) isMod;
        }
    }
}
