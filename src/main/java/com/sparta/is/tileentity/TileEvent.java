package com.sparta.is.tileentity;

import com.sparta.is.tileentity.events.TileEventType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TileEvent
{
    TileEventType value();
}
