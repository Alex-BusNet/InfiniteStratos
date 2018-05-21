package com.sparta.is.entity.driveables.types;

import com.sparta.is.entity.driveables.DriveableType;
import com.sparta.is.utils.vector.Vector3f;

import java.util.ArrayList;

public class UnitType extends DriveableType
{
    public float turnLeftModifier = 1f, turnRightModifier = 1f;
    public boolean squashMobs = false;
    public int stepHeight = 0;
    public float jumpHeight = 1f;
    public float hempVelocity = 1f;
    public float rotateSpeed = 10f;
    public Vector3f leftArmOrigin, rightArmOrigin;
    public float armLength = 1f, legLength = 1f;
    public float heldItemScale = 1f;
    public float height = 3f, width = 2f;
    public float chassisHeight = 1f;

    public float reach = 5f;
    public boolean damageBlocksFromFalling = true;
    public float blockDamageFromFalling = 1f;

    public boolean takeFallDamage = false;
    public float fallDamageMultiplier = 1f;

    public static ArrayList<UnitType> types = new ArrayList<UnitType>();

    public UnitType(TypeFile file)
    {
        super(file);
        types.add(this);
    }

    public static UnitType getUnit(String find)
    {
        for(UnitType type : types)
        {
            if(type.shortName.equals(find))
            {
                return type;
            }
        }

        return null;
    }
}
