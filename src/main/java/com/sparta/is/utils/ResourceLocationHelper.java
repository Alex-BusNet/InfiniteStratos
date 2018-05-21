package com.sparta.is.utils;

import com.sparta.is.entity.driveables.types.BulletType;
import com.sparta.is.entity.driveables.types.InfoType;
import com.sparta.is.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;


public class ResourceLocationHelper
{
    private ResourceLocationHelper() {}
    private static HashMap<InfoType, ResourceLocation> trailTextureMap = new HashMap<InfoType, ResourceLocation>();

    public static ResourceLocation getResourceLocation(String path)
    {
        return new ResourceLocation(Reference.MOD_ID, path);
    }

    public static ModelResourceLocation getModelResourceLocation(String path)
    {
        return new ModelResourceLocation(Reference.MOD_ID + ":" + path);
    }

    public static ModelResourceLocation getModelResourceLocation(String path, String variant)
    {
        return getModelResourceLocation(getResourceLocation(path), variant);
    }

    public static ModelResourceLocation getModelResourceLocation(ResourceLocation rl,  String variant)
    {
        return new ModelResourceLocation(rl, variant);
    }

    public static ResourceLocation getTrailTexture(BulletType bulletType)
    {
        if(trailTextureMap.containsKey(bulletType))
        {
            return trailTextureMap.get(bulletType);
        }
        ResourceLocation resLoc = new ResourceLocation(Reference.MOD_ID, "skins/" + bulletType.trailTexture + ".png");
        trailTextureMap.put(bulletType, resLoc);
        return resLoc;
    }
}
