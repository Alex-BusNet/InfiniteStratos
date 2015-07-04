package com.sparta.is.init;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.entity.EntityIS;
import com.sparta.is.entity.EntityISTabane;
import com.sparta.is.reference.Names;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class ModEntities
{
    public static final EntityIS tabane = new EntityISTabane(FMLClientHandler.instance().getWorldClient());

    public static void init()
    {
        EntityRegistry.registerGlobalEntityID(tabane.getClass(), Names.Villagers.TABANE, tabane.getEntityId());
        EntityRegistry.registerModEntity(tabane.getClass(), Names.Villagers.TABANE, tabane.getEntityId(), InfiniteStratos.instance, 80, 3, false);
        EntityRegistry.addSpawn(tabane.getClass(), 100, 1, 1, EnumCreatureType.creature, BiomeGenBase.plains);

    }



}
