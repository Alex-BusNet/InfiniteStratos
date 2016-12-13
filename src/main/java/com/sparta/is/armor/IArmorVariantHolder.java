package com.sparta.is.armor;

import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IArmorVariantHolder<T extends ArmorIS>
{
    T getArmor();

    String[] getVariants();

    default ItemMeshDefinition getCustomMeshDefinition() { return null; };
}
