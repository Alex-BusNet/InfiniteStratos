package com.sparta.is.core.features;

import com.sparta.is.api.definitions.IItemDefinition;

public interface IFeatureHandler
{
    boolean isFeatureAvailable();

    IItemDefinition getDefinition();

    void register();
}
