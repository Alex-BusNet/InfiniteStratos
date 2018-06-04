/**
 * Custom JSON model loader. Based of off Minecraft Forge's {@link: ForgeBlockStateV1}
 *
 * This class is intended to read non-standard item model files to resolve each IS Unit
 * into its standby, partial deployment, and full deployment states.
 *
 * Created by Sparta
 */

package com.sparta.is.core.client;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.gson.*;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.client.model.BlockStateLoader;
import net.minecraftforge.client.model.ForgeBlockStateV1;

import java.lang.reflect.Type;
import java.util.Map;

public class ISBlockStateV1 extends BlockStateLoader.Marker
{
    Multimap<String, ForgeBlockStateV1.Variant> variants = LinkedHashMultimap.create();

    public static class Deserializer implements JsonDeserializer<ISBlockStateV1>
    {
        static ISBlockStateV1.Deserializer INSTANCE = new Deserializer();

        @Override
        public ISBlockStateV1 deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            LogHelper.info("Deserializing .JSON model...");

            JsonObject json = element.getAsJsonObject();
            ISBlockStateV1 ret = new ISBlockStateV1();
            ret.forge_marker = JsonUtils.getInt(json, "is_marker");

            if(json.has("textures"))
            {
                // Generate Texture resource locations
            }

            Map<String, Map<String, ForgeBlockStateV1.Variant>> condensed = Maps.newLinkedHashMap();
            Multimap<String, ForgeBlockStateV1.Variant> specified = LinkedHashMultimap.create();

            for(Map.Entry<String, JsonElement> e : JsonUtils.getJsonObject(json, "variants").entrySet())
            {
                LogHelper.info("Variant: " + e.getKey());

                JsonObject obj = e.getValue().getAsJsonObject();
                if(obj.has("textures"))
                {
                    // Generate Texture resource locations for variant
                }

                if(obj.has("elements"))
                {
                    JsonArray arr = obj.getAsJsonArray("elements");
                    for(JsonElement arrElement : arr)
                    {
                        LogHelper.info("Element: " + arrElement.getAsString());
                        specified.put(e.getKey(), context.deserialize(arrElement, Variant.class));
                    }
                }
            }

            return ret;
        }
    }
}


