package com.sparta.is.init;

import com.google.common.collect.Maps;
import com.sparta.is.armor.models.ModelUnit;
import com.sparta.is.core.client.model.BakedISUnitModel;
import com.sparta.is.core.reference.Names;

import java.util.Map;

public class ModModels
{
    public static ModelUnit modelByakushiki;
    public static ModelUnit modelKuroAkiko;

    private static final Map<String, ModelUnit> UNIT_MODELS = Maps.newHashMap();
    private static final Map<String, BakedISUnitModel> BAKED_UNIT_MODELS = Maps.newHashMap();

    private ModModels() {}

    public static void preInit()
    {
        modelByakushiki = new ModelUnit(Names.Units.BYAKUSHIKI);
        modelKuroAkiko = new ModelUnit(Names.Units.KURO_AKIKO);

        UNIT_MODELS.put(Names.Units.BYAKUSHIKI, modelByakushiki);
        UNIT_MODELS.put(Names.Units.KURO_AKIKO, modelKuroAkiko);
    }

    public static void addModel(String name, ModelUnit mu)
    {
        UNIT_MODELS.put(name, mu);
    }

    public static void addBakedModel(String name, BakedISUnitModel bakedISUnitModel)
    {
        BAKED_UNIT_MODELS.put(name, bakedISUnitModel);
    }

    public static BakedISUnitModel getBakedModel(String name)
    {
        return BAKED_UNIT_MODELS.getOrDefault(name, null);
    }

    public static Map<String, ModelUnit> GetUnitModels()
    {
        return UNIT_MODELS;
    }

    public static ModelUnit GetModelForUnit(String name)
    {
        return UNIT_MODELS.getOrDefault(name, null);
    }
}
