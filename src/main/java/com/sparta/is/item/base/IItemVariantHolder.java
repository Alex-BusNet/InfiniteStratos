package com.sparta.is.item.base;

import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IItemVariantHolder <T extends ItemIS>
{
    T getItem();

    String[] getVariants();

    default ItemMeshDefinition getCustomMeshDefinition() { return null; };
}
