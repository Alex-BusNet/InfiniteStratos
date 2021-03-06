package com.sparta.is.block;

import net.minecraft.util.IStringSerializable;

public interface IEnumMeta extends IStringSerializable
{
    int getMeta();

    default String getName() { return ((Enum) this).name().toLowerCase(); }
}
