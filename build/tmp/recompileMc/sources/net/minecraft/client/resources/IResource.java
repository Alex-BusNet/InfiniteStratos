package net.minecraft.client.resources;

import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.InputStream;

@SideOnly(Side.CLIENT)
public interface IResource
{
    ResourceLocation getResourceLocation();

    InputStream getInputStream();

    boolean hasMetadata();

    <T extends IMetadataSection> T getMetadata(String p_110526_1_);

    String getResourcePackName();
}