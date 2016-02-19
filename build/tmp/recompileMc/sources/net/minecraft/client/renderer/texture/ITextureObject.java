package net.minecraft.client.renderer.texture;

import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public interface ITextureObject
{
    void setBlurMipmap(boolean p_174936_1_, boolean p_174936_2_);

    void restoreLastBlurMipmap();

    void loadTexture(IResourceManager resourceManager) throws IOException;

    int getGlTextureId();
}