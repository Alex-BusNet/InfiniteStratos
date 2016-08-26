package com.sparta.is.item;

import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IItemVariantHolder<T extends ItemIS>
{
    T getItem();

    String[] getVariants();

    ItemMeshDefinition getCustomMeshDefinition();
}
