package com.sparta.is.core.client.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sparta.is.armor.models.ModelUnit;
import com.sparta.is.core.armor.IISUnit;
import com.sparta.is.core.client.model.format.Armament;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.core.utils.helpers.ModelHelper;
import com.sparta.is.init.ModItems;
import gnu.trove.map.hash.THashMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import org.apache.commons.io.FilenameUtils;
import slimeknights.tconstruct.library.client.CustomTextureCreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ISUnitModelLoader implements ICustomModelLoader
{
    public static String EXTENSION = ".unit";
    private static final String defaultName = "default";

    protected Map<String, List<ResourceLocation>> locations = Maps.newHashMap();

    // Map < IS Unit, Map < Deploy State, Map < Texture reference, texture location > > >
    protected Map<String, Map<String, Map<String, String>>> cache;

    public static ResourceLocation getLocationForUnits(String domain, String unitName)
    {
        return new ResourceLocation(domain, "units/" + unitName + ISUnitModelLoader.EXTENSION);
    }

    public void registerUnitFile(String unit, ResourceLocation location)
    {
        List<ResourceLocation> files = locations.get(unit);

        if(files == null)
        {
            files = Lists.newLinkedList();
            locations.put(unit, files);
        }

        files.add(location);
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation)
    {
        return modelLocation.getResourcePath().endsWith(EXTENSION);
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception
    {
        LogHelper.info("Loader ResourceLocation: " + modelLocation);
        String unitName = FilenameUtils.getBaseName(modelLocation.getResourcePath());
        LogHelper.info("Loader unitName: " + unitName);
        unitName = unitName.toLowerCase(Locale.US);

        if(cache == null)
        {
            cache = new THashMap<>();
            loadFilesToCache();
        }

//        String location = modelLocation.getResourcePath().substring(12); // remove 'models/units/'
        ResourceLocation isUnit = new ResourceLocation(modelLocation.getResourceDomain(), modelLocation.getResourcePath());
        LogHelper.info("Unit resource location: " + isUnit);

        try
        {
            Map<String, JsonElement> deployVariants = ModelHelper.loadDeployVariants(isUnit);
            Map<String, Map<String, String>> textureEntries = ModelHelper.loadTexturesFromJson(isUnit);

            if(!cache.containsKey(unitName))
            {
                cache.put(unitName, new THashMap<>());
            }

            Map<String, Map<String, String>> unitCache = cache.get(unitName);

            LogHelper.info("Adding Textures to cache...");
            for(Map.Entry<String, Map<String, String>> textureEntry : textureEntries.entrySet())
            {
                String unit = textureEntry.getKey().toLowerCase();

                unitCache.put(unit, textureEntry.getValue());
            }
        }
        catch(IOException e)
        {
            LogHelper.info("No IS Unit model found at " + isUnit + ", skipping\n");
        }
        catch(JsonParseException e)
        {
            LogHelper.info("Cannot load IS Unit model for " + isUnit, e);
            throw e;
        }

        ModelUnit model = new ModelUnit();
        Map<String, Map<String, ArrayList<Armament>>> armaments = ModelHelper.loadArmamentFromJson(isUnit);

        if(cache.containsKey(unitName))
        {
            for(Map.Entry<String, Map<String, String>> entry : cache.get(unitName).entrySet())
            {
                IISUnit unit = ModItems.getUnit(entry.getKey());

                if(unit != null && unit.hasTexturePerMaterial())
                {
                    for(Map.Entry<String, String> e : entry.getValue().entrySet())
                        CustomTextureCreator.registerTexture(new ResourceLocation(e.getValue()));
                }
            }
        }
        else
        {
            LogHelper.info("Tried to load models for " + unitName + " but none were found");
        }

        LogHelper.info("");
        return model;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager)
    {
        cache = null;
    }

    private void loadFilesToCache()
    {
        cache.put(defaultName, new THashMap<>());

        for(Map.Entry<String, List<ResourceLocation>> entry : locations.entrySet())
        {
            String unit = entry.getKey();
            List<ResourceLocation> modLocations = entry.getValue();
            for(ResourceLocation location : modLocations)
            {
                try
                {
                    Map<String, Map<String, String>> textureEntries = ModelHelper.loadTexturesFromJson(location);

                    for(Map.Entry<String, Map<String, String>> textureEntry : textureEntries.entrySet())
                    {
                        String unitName = textureEntry.getKey().toLowerCase();
                        Map<String, String> texture = textureEntry.getValue();

                        if(!cache.containsKey(unitName))
                        {
                            cache.put(unitName, new THashMap<>());
                        }

                        if(!cache.get(unitName).containsKey(unit))
                        {
                            cache.get(unitName).put(unit, texture);
                        }
                    }
                }
                catch(IOException e)
                {
                    LogHelper.error("Cannot load unit model " + location, e);
                }
                catch(JsonParseException e)
                {
                    LogHelper.error("Cannot load unit model " + location, e);
                    throw e;
                }
            }

            if(!cache.get(defaultName).containsKey(unit))
            {
                LogHelper.debug(unit + " Unit model does not contain a default entry");
            }
        }

        Map<String, Map<String, String>> defaults = cache.get(defaultName);

//        Iterator<Map.Entry<String, Map<String, Map<String, String>>>> unitEntryIter = cache.entrySet().iterator();

        for(Map.Entry<String, Map<String, Map<String, String>>> unitEntryIter : cache.entrySet())
        {
            Map<String, Map<String,String>> variantTextures = unitEntryIter.getValue();

            for(Map.Entry<String, Map<String, String>> defaultEntry : defaults.entrySet())
            {
                if(!variantTextures.containsKey(defaultEntry.getKey()))
                {
                    LogHelper.debug("Filling in default for unit {}", defaultEntry.getKey());
                    variantTextures.put(defaultEntry.getKey(), defaultEntry.getValue());
                }
            }
        }
    }
}
