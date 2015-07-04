package com.sparta.is.proxy;

public class ServerProxy extends CommonProxy
{

    @Override
    public ClientProxy getClientProxy()
    {
        return null;
    }

    @Override
    public void initRenderingAndTextures()
    {
        //NOOP
    }

    @Override
    public void registerEventHandlers()
    {
        //NOOP
    }

    @Override
    public void registerKeyBindings()
    {
        //NOOP
    }

    @Override
    public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
    {
        //NOOP
    }

}
