package com.sparta.is.proxy;

public interface IProxy
{
    ClientProxy getClientProxy();

    void registerEventHandlers();

    void registerKeyBindings();
}
