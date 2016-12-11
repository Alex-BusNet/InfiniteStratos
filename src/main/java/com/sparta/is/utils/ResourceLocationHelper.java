package com.sparta.is.utils;

import com.sparta.is.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;


public class ResourceLocationHelper
{
    private ResourceLocationHelper() {}

    public static ResourceLocation getResourceLocation(String path)
    {
        return new ResourceLocation(Reference.MOD_ID, path);
    }

    public static ModelResourceLocation getModelResourceLocation(String path)
    {
        return new ModelResourceLocation(Reference.MOD_ID + ":" + path);
    }
}
