package com.sparta.is.reference;

public enum EnumAttachmentType
{
    BARREL, STOCK, SIGHTS, GRIP, GENERIC;

    public static EnumAttachmentType get(String s)
    {
        for(EnumAttachmentType type: values())
        {
            if(type.toString().toLowerCase().equals(s))
            {
                return type;
            }
        }

        return GENERIC;
    }
}
