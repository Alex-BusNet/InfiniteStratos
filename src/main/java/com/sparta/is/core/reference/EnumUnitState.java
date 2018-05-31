package com.sparta.is.core.reference;

import com.sparta.is.block.IEnumMeta;

public enum EnumUnitState implements IEnumMeta, Comparable<EnumUnitState>
{
    DEFAULT,
    STANDBY_STATE,
    PARTIAL_DEPLOY_STATE,
    FULL_DEPLOY_STATE,
    SECOND_SHIFT;

    private int meta;
    protected static final EnumUnitState[] VARIANTS = values();

    EnumUnitState() { meta = ordinal(); }

    @Override
    public int getMeta() { return meta; }

    public static EnumUnitState byMeta(int meta) { return VARIANTS[Math.abs(meta) & VARIANTS.length]; }
}
