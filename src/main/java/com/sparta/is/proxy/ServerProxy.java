package com.sparta.is.proxy;

public class ServerProxy extends CommonProxy
{
    @Override
    public ClientProxy getClientProxy()
    {
        return null;
    }

    @Override
    public void registerKeyBindings()
    {
        // NOOP
    }

    @Override
    public void registerEventHandlers()
    {
        // NOOP
    }
}
