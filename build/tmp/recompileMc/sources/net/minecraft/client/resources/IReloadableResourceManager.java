package net.minecraft.client.resources;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public interface IReloadableResourceManager extends IResourceManager
{
    void reloadResources(List<IResourcePack> p_110541_1_);

    void registerReloadListener(IResourceManagerReloadListener reloadListener);
}