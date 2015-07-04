package com.sparta.is.api.util;

import net.minecraftforge.common.util.ForgeDirection;

public interface IOrientable
{
    /**
     * @return true or false, if the tile rotation is meaningful, or even changeable
     */
    boolean canBeRotated();

    /**
     * @return the direction the tile is facing
     */
    ForgeDirection getForward();

    /**
     * @return the direction top of the tile
     */
    ForgeDirection getUp();

    /**
     * Update the orientation
     *
     * @param Forward new forward direction
     * @param Up      new upwards direction
     */
    void setOrientation( ForgeDirection Forward, ForgeDirection Up );
}
