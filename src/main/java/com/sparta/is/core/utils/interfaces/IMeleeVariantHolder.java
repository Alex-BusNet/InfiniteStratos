package com.sparta.is.core.utils.interfaces;

import com.sparta.is.core.item.ItemISMelee;

public interface IMeleeVariantHolder<T extends ItemISMelee>
{
    T getItem();

    String[] getVariants();
}
