package com.sparta.is.core.utils.interfaces;

import com.sparta.is.core.reference.Key;
import net.minecraft.entity.player.EntityPlayer;

public interface IKeyBound
{
    void doKeyBindingAction(EntityPlayer entityPlayer, Key key, boolean falling);
}
