package com.sparta.is.reference;

public enum Key
{
    UNKNOWN, STANDBY, PARTIAL_DEPLOY, FULL_DEPLOY, EQUALIZER_ACCESS_MODIFIER, ONE_OFF_ABILITY;

    public static final Key[] KEYS = Key.values();

    public static Key getKey(byte ordinal)
    {
        return KEYS[ordinal % KEYS.length];
    }

}

