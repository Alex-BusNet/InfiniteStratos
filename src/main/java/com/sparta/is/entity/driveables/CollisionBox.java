package com.sparta.is.entity.driveables;

import org.lwjgl.util.vector.Vector3f;

public class CollisionBox
{
    public float x, y, z;
    public float width, height, depth;
    public int health;
    public EnumDriveablePart part;

    public CollisionBox(int health, int x, int y, int z, int w, int h, int d)
    {
        this.health = health;
        this.x = x / 16f;
        this.y = y / 16f;
        this.z = z / 16f;
        this.width = w / 16f;
        this.height = h / 16f;
        this.depth = d / 16f;
    }

    public Vector3f getCenter()
    {
        return new Vector3f(x + width / 2f, y + height / 2f, z + depth / 2f);
    }
}
