package com.sparta.is.core.client.model.format;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.sparta.is.core.utils.helpers.LogHelper;

import java.lang.reflect.Type;
import java.util.Map;

public class DeployVariants implements JsonDeserializer<Map<String, JsonElement>>
{
    public static final DeployVariants INSTANCE = new DeployVariants();
    public static final Type TYPE = new TypeToken<Map<String, JsonElement>>() {}.getType();

    private static Gson GSON = new Gson();

    @Override
    public Map<String, JsonElement> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        LogHelper.info("Deploy Variant search");

        JsonObject obj = json.getAsJsonObject();
        JsonElement elem = obj.get("variants");

        if(elem == null)
            throw new JsonParseException("Missing {variants} tag");

        obj = elem.getAsJsonObject();

        Map<String, JsonElement> variants = Maps.newHashMap();

        if(obj.has("full_deploy"))
            variants.put("full_deploy", obj.get("full_deploy"));
        else
            throw new JsonParseException("Missing {full_deploy} variant");

        if(obj.has("partial_deploy"))
            variants.put("partial_deploy", obj.get("partial_deploy"));
        else
            throw new JsonParseException("Missing {partial_deploy} variant");

        if(obj.has("standby"))
            variants.put("standby", obj.get("standby"));
        else
            throw new JsonParseException("Missing {standby} variant");

        return variants;
    }
}
