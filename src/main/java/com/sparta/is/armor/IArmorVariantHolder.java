package com.sparta.is.armor;

import com.sparta.is.core.armor.ArmorIS;

public interface IArmorVariantHolder<T extends ArmorIS>
{
    T getArmor();

    String[] getVariants();
}
