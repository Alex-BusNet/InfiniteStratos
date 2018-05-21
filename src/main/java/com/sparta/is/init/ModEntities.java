package com.sparta.is.init;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.entity.EntityIS;
import com.sparta.is.entity.EntityTabane;
import com.sparta.is.reference.Names;
import com.sparta.is.utils.ResourceLocationHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModEntities
{
    public static final List<EntityIS> ENTITIES = new ArrayList<EntityIS>();

    public static EntityTabane tabane = new EntityTabane(FMLClientHandler.instance().getWorldClient());

    private ModEntities() {}

    public static void init()
    {
        EntityRegistry.registerModEntity(ResourceLocationHelper.getResourceLocation(Names.Villagers.TABANE), EntityTabane.class, "tabane", 0, InfiniteStratos.instance, 64, 1, false);
    }

    public static Collection<EntityIS> getEntities()
    {
        return ENTITIES;
    }

    public static void registerEntity(EntityIS entityIS)
    {
        ENTITIES.add(entityIS);
    }
}
