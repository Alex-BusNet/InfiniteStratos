package com.sparta.is.item.base;

import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IMeleeVariantHolder<T extends ItemISMelee>
{
    T getItem();

    String[] getVariants();

    default ItemMeshDefinition getCustomMeshDefinition() { return null; };
}
