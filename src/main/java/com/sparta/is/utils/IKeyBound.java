package com.sparta.is.utils;

import com.sparta.is.reference.Key;
import net.minecraft.entity.player.EntityPlayer;

public interface IKeyBound
{
    void doKeyBindingAction(EntityPlayer entityPlayer, Key key, boolean falling);
}
