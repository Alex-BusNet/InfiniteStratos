package com.sparta.is.core.client.model.format;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.core.utils.helpers.ModelHelper;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Armament
{
    public Map<String, ResourceLocation> textures;
    public Map<String, ArmamentBlock> blocks;

    public static class ArmamentBlock
    {
        ArmamentBlock()
        {
            from = new Vector3f(0.0f, 0.0f, 0.0f);
            to = new Vector3f(1.0f, 1.0f, 1.0f);
            rotation = null;

            for(EnumFacing ef : ModelHelper.MODEL_SIDES)
            {
                faces.put(ef, new HashMap<>());
            }
        }

        Vector3f from;
        Vector3f to;

        @Nullable
        BlockPartRotation rotation;

        // EnumFacing = Face of block, String = Texture reference string, BlockFaceUV = UV of block relative to texture
        Map<EnumFacing, Map<String, BlockFaceUV>> faces;
    }

    public static class ArmamentDeserializer implements JsonDeserializer<Map<String, Map<String, ArrayList<Armament>>>>
    {
        public static final ArmamentDeserializer INSTANCE = new ArmamentDeserializer();
        public static final Type TYPE = new TypeToken<Armament>() {}.getType();

        private static final String[] VARIANTS = new String[] {"full_deploy", "partial_deploy", "standby"};
        private static final String[] PARTS = new String[] { "rightarm", "leftarm", "chest", "rightshoulder", "leftshoulder", "rightwing", "leftwing", "rightleg", "leftleg", "rightfoot", "leftfoot", "headpiece" };

        private static Gson GSON = new Gson();

        @Override
        public Map<String, Map<String, ArrayList<Armament>>> deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            LogHelper.info("Parsing Armaments...");
            JsonObject obj = element.getAsJsonObject();
            LogHelper.info("Armament element: " + obj.toString());
            JsonElement elem = obj.get("variants");

            obj = elem.getAsJsonObject();

            Map<String, Map<String, ArrayList<Armament>>> armaments = Maps.newHashMap();

            for ( int i = 0; i < VARIANTS.length; i++ )
            {
                // Every unit must have at least one armament for each variant.
                // An error is thrown if the unit does not.
                LogHelper.info("Parsing armaments for " + VARIANTS[i] + " deployment variant...");
                if ( obj.has(VARIANTS[i]) )
                {
                    JsonElement jsonVariant = obj.get(VARIANTS[i]);
                    JsonObject jsonObj = jsonVariant.getAsJsonObject();
                    jsonVariant = jsonObj.get("armaments");
                    LogHelper.info("JsonVariant: " + jsonVariant);

                    jsonObj = jsonVariant.getAsJsonObject();

                    Map<String, ArrayList<Armament>> partModel = Maps.newHashMap();
                    ArrayList<Armament> subparts = new ArrayList<>();

                    for ( int j = 0; j < PARTS.length; j++ )
                    {
                        // Not every unit, or deployment variant, will have a model
                        // for each part.
                        if ( jsonObj.has(PARTS[j]) )
                        {
                            JsonElement part = jsonObj.get(PARTS[j]);
                            JsonObject partObj = part.getAsJsonObject();
                            JsonArray elements = partObj.get("elements").getAsJsonArray();

                            for ( int k = 0; k < elements.size(); k++ )
                            {
                                JsonObject o = elements.get(k).getAsJsonObject();
                                subparts.add(GSON.fromJson(o, TYPE));
                            }

                            partModel.put(PARTS[j], subparts);
                        }
                        else
                        {
                            LogHelper.debug(PARTS[j] + " is missing for variant " + VARIANTS[i] + ". This may or may not be an error as the parts are unit dependent.");
                        }
                    }

                    armaments.put(VARIANTS[i], partModel);
                }
                else
                {
                    throw new JsonParseException(VARIANTS[i] + " is missing from json file.");
                }
            }

            LogHelper.info("Full deployment armament keys: " + armaments.get(VARIANTS[0]).keySet());
            LogHelper.info("Partial deployment armament keys: " + armaments.get(VARIANTS[1]).keySet());
            LogHelper.info("Standby deployment armament keys: " + armaments.get(VARIANTS[2]).keySet());

            return armaments;
        }
    }


}
