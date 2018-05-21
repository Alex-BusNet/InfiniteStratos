package com.sparta.is.entity.driveables.types;

import net.minecraft.item.ItemStack;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;

public class EqualizerMeleeType extends InfoType
{
    public boolean isShield = false;
    public Vector3f shieldOrigin;
    public Vector3f shieldDimensions;
    // Absorption is between 0 and 1
    public float shieldDamageAbsorption = 0f;

    public boolean oneHanded = false;
    public int delayBetweenMeleeStrikes = 1;
    public ArrayList<Vector3f> meleePath = new ArrayList<Vector3f>(), meleePathAngles = new ArrayList<Vector3f>();
    public ArrayList<Vector3f> meleeDamagePoints = new ArrayList<Vector3f>();
    public float meleeDamage = 1f;

    public EqualizerMeleeType(TypeFile file)
    {
        super(file);
    }

    public float getMeleeDamage(ItemStack stack)
    {
        return meleeDamage;
    }
}
