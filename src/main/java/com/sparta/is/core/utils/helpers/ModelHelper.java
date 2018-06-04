package com.sparta.is.core.utils.helpers;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.sparta.is.core.client.model.format.Armament;
import com.sparta.is.core.client.model.format.DeployVariants;
import com.sparta.is.core.client.model.format.TextureDeserializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

public class ModelHelper
{
    public static final EnumFacing[] MODEL_SIDES = new EnumFacing[] { null, EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST };

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Armament.ArmamentDeserializer.TYPE, Armament.ArmamentDeserializer.INSTANCE)
            .registerTypeAdapter(TextureDeserializer.TYPE, TextureDeserializer.INSTANCE)
            .registerTypeAdapter(DeployVariants.TYPE, DeployVariants.INSTANCE)
            .create();

    public static Reader getReaderForResource(ResourceLocation location) throws IOException
    {
        return getReaderForResource(location, Minecraft.getMinecraft().getResourceManager());
    }

    public static Reader getReaderForResource(ResourceLocation location, IResourceManager manager) throws IOException
    {
        ResourceLocation file = new ResourceLocation(location.getResourceDomain(), location.getResourcePath() + ".json");
        IResource resource = manager.getResource(file);
        return new BufferedReader(new InputStreamReader(resource.getInputStream(), Charsets.UTF_8));
    }

    public static Map<String, Map<String, String>> loadTexturesFromJson(ResourceLocation location) throws IOException
    {
        Reader reader = getReaderForResource(location);
        try
        {
            return GSON.fromJson(reader, TextureDeserializer.TYPE);
        }
        finally {
            IOUtils.closeQuietly(reader);
        }
    }

    public static Map<String, Map<String, ArrayList<Armament>>> loadArmamentFromJson(ResourceLocation location) throws IOException
    {
        Reader reader = getReaderForResource(location);
        try
        {
            return GSON.fromJson(reader, Armament.ArmamentDeserializer.TYPE);
        }
        finally
        {
            IOUtils.closeQuietly(reader);
        }
    }

    public static Map<String, JsonElement> loadDeployVariants(ResourceLocation location) throws IOException
    {
        Reader reader = getReaderForResource(location);
        try
        {
            return GSON.fromJson(reader, DeployVariants.TYPE);
        }
        finally
        {
            IOUtils.closeQuietly(reader);
        }
    }
}
