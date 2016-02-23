package com.sparta.is.api.util;

import net.minecraft.util.EnumFacing;

public interface IOrientable
{
    /**
     * @return true or false, if the tile rotation is meaningful, or even changeable
     */
    boolean canBeRotated();

    /**
     * @return the direction the tile is facing
     */
    EnumFacing getForward();

    /**
     * @return the direction top of the tile
     */
    EnumFacing getUp();

    /**
     * Update the orientation
     *
     * @param Forward new forward direction
     * @param Up      new upwards direction
     */
    void setOrientation( EnumFacing Forward, EnumFacing Up );
}
