package com.sparta.is.entity.driveables.types;

import com.sparta.is.entity.EntityISUnit;
import com.sparta.is.entity.driveables.EntityBullet;
import com.sparta.is.item.ItemAttachment;
import com.sparta.is.item.ItemBullet;
import com.sparta.is.item.base.ItemISMelee;
import com.sparta.is.item.base.ItemISRange;

public enum EnumType
{
    part("parts"), bullet("bullets"), melee_equalizer("meleeEqualizers"), range_equalizer("rangeEqualizers"), unit("units"),
    attachment("attachments");

    public String folderName;

    private EnumType(String s)
    {
        folderName = s;
    }

    public static EnumType get(String s)
    {
        for(EnumType e : values())
        {
            if(e.folderName.equals(s))
            {
                return e;
            }
        }

        return null;
    }

    public Class<? extends InfoType> getTypeClass()
    {
        switch(this)
        {
            case bullet:            return BulletType.class;
            case melee_equalizer:   return EqualizerMeleeType.class;
            case range_equalizer:   return EqualizerRangeType.class;
            case unit:              return UnitType.class;
            case attachment:        return AttachmentType.class;
            default:                return InfoType.class;
        }
    }

    public static EnumType getFromObject(Object o)
    {
        if(o instanceof UnitType || o instanceof EntityISUnit) return unit;
        else if(o instanceof BulletType || o instanceof ItemBullet || o instanceof EntityBullet ) return bullet;
        else if(o instanceof AttachmentType || o instanceof ItemAttachment) return attachment;
        else if(o instanceof EqualizerRangeType || o instanceof ItemISRange) return range_equalizer;
        else if(o instanceof EqualizerMeleeType || o instanceof ItemISMelee) return melee_equalizer;
        return null;
    }

}
