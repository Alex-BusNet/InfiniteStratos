package com.sparta.is.utility;

import com.sparta.is.reference.Key;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound
{
    public abstract void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key);
}
