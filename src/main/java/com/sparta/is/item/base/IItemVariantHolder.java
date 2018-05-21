package com.sparta.is.item.base;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.Item;

public interface IItemVariantHolder <T extends Item>
{
    T getItem();

    String[] getVariants();

    default ItemMeshDefinition getCustomMeshDefinition() { return null; };
}
