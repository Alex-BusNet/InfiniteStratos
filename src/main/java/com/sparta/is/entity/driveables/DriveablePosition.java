package com.sparta.is.entity.driveables;

import org.lwjgl.util.vector.Vector3f;

public class DriveablePosition
{
    /** The position */
    public Vector3f position;
    /** The part this is attached to */
    public EnumDriveablePart part;

    public DriveablePosition(Vector3f v, EnumDriveablePart p)
    {
        position = v;
        part = p;
    }

    public DriveablePosition(String[] split)
    {
        this(new Vector3f(Float.parseFloat(split[1]) / 16F, Float.parseFloat(split[2]) / 16F, Float.parseFloat(split[3]) / 16F), EnumDriveablePart.getPart(split[4]));
    }
}
