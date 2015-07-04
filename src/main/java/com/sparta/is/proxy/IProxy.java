package com.sparta.is.proxy;

import net.minecraft.client.model.ModelBiped;

public interface IProxy
{
    public abstract ClientProxy getClientProxy();

    public abstract void initRenderingAndTextures();

    public abstract void registerEventHandlers();

    public abstract void registerKeyBindings();

    public abstract void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch);

    public abstract ModelBiped getArmorModel(int id);


}