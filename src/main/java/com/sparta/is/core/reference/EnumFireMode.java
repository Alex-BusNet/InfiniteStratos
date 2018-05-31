package com.sparta.is.core.reference;

public enum EnumFireMode
{
    SEMI_AUTO, FULL_AUTO, BURST;

    public static EnumFireMode getFireMode(String s)
    {
        s = s.toLowerCase();
        if ( s.equals("full_auto") ) { return FULL_AUTO; }
        else if(s.equals("burst")) { return BURST; }
        else return SEMI_AUTO;
    }
}
