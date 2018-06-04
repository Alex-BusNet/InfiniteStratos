package com.sparta.is.core.utils.interfaces;

import net.minecraft.item.Item;

public interface IItemVariantHolder <T extends Item>
{
    T getItem();

    String[] getVariants();
}
