package com.sparta.is.proxy;

public class ClientProxy extends CommonProxy
{
    @Override
    public ClientProxy getClientProxy()
    {
        return this;
    }

    @Override
    public void registerKeyBindings()
    {

    }

    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
    }
}
