package com.sparta.is.init;

import com.sparta.is.entity.EntityIS;
import com.sparta.is.entity.EntityISTabane;
import com.sparta.is.reference.Names;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.awt.*;

public class ModEntities
{
    public static final EntityIS tabane = new EntityISTabane(FMLClientHandler.instance().getWorldClient());

    public static void init(Object is)
    {
        EntityRegistry.registerModEntity(tabane.getClass(), Names.Villagers.TABANE, tabane.getEntityId(), is, 10, 10, false, Color.MAGENTA.getRGB(), Color.BLACK.getRGB());
//        EntityRegistry.registerModEntity(tabane.getClass(), Names.Villagers.TABANE, tabane.getEntityId(), InfiniteStratos.instance, 80, 3, false);
//        EntityRegistry.addSpawn(tabane.getClass(), 100, 1, 1, EnumCreatureType.creature, BiomeGenBase.plains);

    }



}
