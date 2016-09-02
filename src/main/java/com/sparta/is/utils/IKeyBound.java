package com.sparta.is.utils;

import com.sparta.is.reference.Key;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound
{
    void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key, boolean falling);
}
