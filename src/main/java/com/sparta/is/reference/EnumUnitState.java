package com.sparta.is.reference;

import com.sparta.is.block.IEnumMeta;

public enum EnumUnitState implements IEnumMeta, Comparable<EnumUnitState>
{
    STANDBY_STATE,
    PARTIAL_DEPLOY_STATE,
    FULL_DEPLOY_STATE;

    private int meta;
    protected static final EnumUnitState[] VARIANTS = values();

    EnumUnitState() { meta = ordinal(); }

    @Override
    public int getMeta() { return meta; }

    public static EnumUnitState byMeta(int meta) { return VARIANTS[Math.abs(meta) & VARIANTS.length]; }
}
