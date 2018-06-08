package com.sparta.is.core.client.model.format;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

public class Armament
{
    // String = State, Map< String = reference name, ResourceLocation = file >
    public Map<String, Map<String, ResourceLocation>> textures = Maps.newHashMap();
    public Map<String, ArmamentBlock> subblocks = Maps.newHashMap();
    public String armamentName = "";

    public static class ArmamentBlock
    {
        @Nonnull
        Vector3f from;
        @Nonnull
        Vector3f to;

        @Nullable
        BlockPartRotation rotation;

        // EnumFacing = Face of block, String = Texture reference string, BlockFaceUV = UV of block relative to texture
        Map<EnumFacing, Pair<String, BlockFaceUV>> faces = Maps.newHashMap();

        @Override
        public String toString()
        {
            String s = "";
            s += "\n\t\t\tFrom: [ " + from.toString() + " ]\n";
            s += "\t\t\tTo: [ " + to.toString() + " ]\n";
            if(rotation != null)
            {
                s += "\t\t\tRotation: [ Angle: " + rotation.angle + ", Axis: " + rotation.axis.toString() + ", Origin: " + rotation.origin + " ]\n";
            }
            s += "\t\t\tFaces: { \n";
            for(Map.Entry<EnumFacing, Pair<String, BlockFaceUV>> f : faces.entrySet())
            {
                s += "\t\t\t\t[ (" + f.getKey().getName() + ": " + f.getValue().getKey() + ", { " + Arrays.toString(f.getValue().getValue().uvs) + ", Rotation: " + f.getValue().getValue().rotation + " } ) ]\n";
            }

            s += "\t\t\t}";

            return s;
        }
    }

    @Override
    public String toString()
    {
        String s = "";
        s += "\n\tName: " + armamentName + " ,\n";
        s += "\tTextures: { \n";
        for(Map.Entry<String, Map<String, ResourceLocation>> t : textures.entrySet())
        {
            s += "\t" + t.getKey() + ": " + t.getValue() + "\n";
        }

        s += "\t},\n\tSubblocks: {\n";

        for(Map.Entry<String, ArmamentBlock> b : subblocks.entrySet())
        {
            s += "\t\t" + b.getKey() + " { " + b.getValue().toString() + "\n\t\t}\n";
        }

        s += "\t}";

        return s;
    }

    public static class ArmamentDeserializer implements JsonDeserializer<Map<String, Map<String, Armament>>>
    {
        public static final ArmamentDeserializer INSTANCE = new ArmamentDeserializer();
        public static final Type TYPE = new TypeToken<Armament>() {}.getType();

        private static final String[] VARIANTS = new String[] {"full_deploy", "partial_deploy", "standby"};
        private static final String[] PARTS = new String[] { "rightarm", "leftarm", "chest", "rightshoulder", "leftshoulder", "rightwing", "leftwing", "rightleg", "leftleg", "rightfoot", "leftfoot", "headpiece" };
        private static final String[] FACES = new String[] { "north", "south", "east", "west", "up", "down" };
        private static Gson GSON = new Gson();

        @Override
        public Map<String, Map<String, Armament>> deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {

            LogHelper.info("Parsing Armaments...");
            JsonObject obj = element.getAsJsonObject();
            JsonElement elem = obj.get("variants");

            obj = elem.getAsJsonObject();

            // Map< Deployment Variant type, Map< Armament name, Armament object>>
            //  Deployment variant = "full_deploy", "partial_deploy", or "standby"
            //  Armament name = "rightarm", "leftarm", etc...
            //  Armament object = object containing all subblocks for the armament and relavent textures
            Map<String, Map<String, Armament>> armaments = Maps.newHashMap();

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

                    jsonObj = jsonVariant.getAsJsonObject();

                    Map<String, Armament> partModel = Maps.newHashMap();

                    for ( int j = 0; j < PARTS.length; j++ )
                    {
                        // Not every unit, or deployment variant, will have a model
                        // for each part.
                        if ( jsonObj.has(PARTS[j]) )
                        {
                            JsonElement part = jsonObj.get(PARTS[j]);
                            JsonObject partObj = part.getAsJsonObject();
                            JsonArray elements = partObj.get("elements").getAsJsonArray();

                            Armament armament = new Armament();
                            for ( int k = 0; k < elements.size(); k++ )
                            {
                                JsonObject o = elements.get(k).getAsJsonObject();
                                Armament.ArmamentBlock ab = new ArmamentBlock();
                                JsonArray vecArr;

                                if ( o.has("from") )
                                {
                                    vecArr = o.get("from").getAsJsonArray();
                                    ab.from = new Vector3f(vecArr.get(0).getAsFloat(), vecArr.get(1).getAsFloat(), vecArr.get(2).getAsFloat());
                                }
                                else
                                {
                                    throw new JsonParseException("Element " + k + " is missing 'from' tag in json file.");
                                }

                                if ( o.has("to") )
                                {
                                    vecArr = o.get("to").getAsJsonArray();
                                    ab.to = new Vector3f(vecArr.get(0).getAsFloat(), vecArr.get(1).getAsFloat(), vecArr.get(2).getAsFloat());
                                }
                                else
                                {
                                    throw new JsonParseException("Element " + k + " is missing 'to' tag in json file.");
                                }

                                // Get Block rotation (if exists)
                                if ( o.has("rotation") )
                                {
                                    JsonElement rotElem = o.get("rotation");
                                    vecArr = (rotElem.getAsJsonObject()).get("origin").getAsJsonArray();
                                    Vector3f origin = new Vector3f(vecArr.get(0).getAsFloat(), vecArr.get(1).getAsFloat(), vecArr.get(2).getAsFloat());
                                    String axis = (rotElem.getAsJsonObject()).get("axis").getAsString();
                                    float ang = (rotElem.getAsJsonObject()).get("angle").getAsFloat();

                                    ab.rotation = new BlockPartRotation(origin, EnumFacing.Axis.byName(axis), ang, false);
                                }
                                else
                                {
                                    ab.rotation = null;
                                    LogHelper.debug("Element " + k + " does not have 'rotation' tag in json file. This may or may not be an error as this tag is not required");
                                }

                                // Get the texture settings, per face.
                                if ( o.has("faces") )
                                {
                                    JsonElement faceElem = o.get("faces");

                                    Map<EnumFacing, Pair<String, BlockFaceUV>> parsedFaces = Maps.newHashMap();
                                    JsonObject faceObj = faceElem.getAsJsonObject();

                                    for ( String s : FACES )
                                    {
                                        if(faceObj.has(s))
                                        {
                                            JsonElement face = faceObj.get(s);

                                            String textureRef = face.getAsJsonObject().get("texture").toString();

                                            int rotation = 0;
                                            if ( face.getAsJsonObject().has("rotation") )
                                            {
                                                rotation = face.getAsJsonObject().get("rotation").getAsInt();
                                            }

                                            float[] uvCoord = new float[]{0, 0, 0, 0};
                                            JsonArray uvCoordArr = face.getAsJsonObject().get("uv").getAsJsonArray();

                                            for ( int h = 0; h < 4; h++ )
                                            {
                                                uvCoord[h] = uvCoordArr.get(h).getAsFloat();
                                            }

                                            parsedFaces.put(EnumFacing.byName(s), Pair.of(textureRef, new BlockFaceUV(uvCoord, rotation)));
                                        }
                                        else
                                        {
                                            throw new JsonParseException("Element " + k + " is missing definition for face " + s);
                                        }
                                    }

                                    ab.faces = parsedFaces;
                                }
                                else
                                {
                                    throw new JsonParseException("Element " + k + " is missing 'faces' tag in json file.");
                                }

                                JsonElement nameElem = o.get("name");
                                armament.subblocks.put((nameElem != null) ? nameElem.toString() : String.valueOf(k), ab);
                            }

                            armament.armamentName = PARTS[j];
                            partModel.put(PARTS[j], armament);
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

            return armaments;
        }
    }


}
