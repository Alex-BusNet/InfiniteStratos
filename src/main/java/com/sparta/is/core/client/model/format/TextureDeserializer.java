package com.sparta.is.core.client.model.format;

import com.google.common.collect.Maps;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class TextureDeserializer implements JsonDeserializer<Map<String, Map<String, String>>>
{
    public static final TextureDeserializer INSTANCE = new TextureDeserializer();
    public static final Type TYPE = new TypeToken<Map<String, String>>() {}.getType();

    private static final String[] VARIANTS = new String[]{"full_deploy", "partial_deploy", "standby"};

    private static final Gson GSON = new Gson();

    @Override
    public Map<String,Map<String, String>> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject obj = json.getAsJsonObject();
        JsonElement elem = obj.get("variants");

        if(elem == null)
        {
            throw new JsonParseException("Missing variants entry in json");
        }

        Map<String, Map<String, String>> textureMap = Maps.newHashMap();
        obj = elem.getAsJsonObject();

        for(int i = 0; i < VARIANTS.length; i++)
        {
            elem = obj.get(VARIANTS[i]);
            JsonObject variantObj = elem.getAsJsonObject();
            JsonElement texElem = variantObj.get("textures");
            textureMap.put(VARIANTS[i], GSON.fromJson(texElem, TYPE));
        }

        return textureMap;
    }
}
