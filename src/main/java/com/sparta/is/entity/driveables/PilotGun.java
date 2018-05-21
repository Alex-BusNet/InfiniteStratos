package com.sparta.is.entity.driveables;

import com.sparta.is.entity.driveables.types.EqualizerRangeType;
import org.lwjgl.util.vector.Vector3f;

public class PilotGun extends DriveablePosition
{
    public EqualizerRangeType type;

    public PilotGun(String[] split)
    {
        super(new Vector3f(Float.parseFloat(split[1]) / 16F, Float.parseFloat(split[2]) / 16F, Float.parseFloat(split[3]) / 16F), EnumDriveablePart.getPart(split[4]));
        type = EqualizerRangeType.getGun(split[5]);
    }
}
