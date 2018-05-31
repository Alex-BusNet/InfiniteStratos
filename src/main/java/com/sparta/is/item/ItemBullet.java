package com.sparta.is.item;

import com.sparta.is.entity.driveables.types.BulletType;
import com.sparta.is.core.item.ItemIS;

public class ItemBullet extends ItemIS
{
    public BulletType type;

    public ItemBullet()
    {
        super("Generic Bullet");
    }
}
