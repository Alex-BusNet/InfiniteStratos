package com.sparta.is.entity.driveables;

import java.util.ArrayList;

public enum EnumDriveablePart
{
    core(new EnumDriveablePart[] { }, "isCore", "Core"),
    absoluteDefense(new EnumDriveablePart[] { }, "isAbsoluteDefense", "Absolute Defense System"),
    processor(new EnumDriveablePart[] { }, "isProcessor", "IS Unit Processor");
    //TODO: Add more parts

    private String shortName;
    private String name;
    private EnumDriveablePart[] children;

    private EnumDriveablePart(EnumDriveablePart[] parts, String s, String s2)
    {
        children = parts;
        shortName = s;
        name = s2;
    }

    public EnumDriveablePart[] getChildren()
    {
        return children;
    }

    public EnumDriveablePart[] getParents()
    {
        ArrayList<EnumDriveablePart> parents = new ArrayList<EnumDriveablePart>();
        for(EnumDriveablePart part : values())
        {
            for(EnumDriveablePart childPart : part.getChildren())
            {
                if(childPart == this)
                {
                    parents.add(part);
                }
            }
        }
        return parents.toArray(new EnumDriveablePart[parents.size()]);
    }

    public String getShortName()
    {
        return shortName;
    }

    public String getName()
    {
        return name;
    }

    /** For reading parts from driveable files */
    public static EnumDriveablePart getPart(String s)
    {
        for(EnumDriveablePart part : values())
            if(part.getShortName().equals(s))
                return part;
        return null;
    }
}
