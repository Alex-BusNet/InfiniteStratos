package com.sparta.is.core.utils.interfaces;

import com.sparta.is.core.item.ItemISMelee;
import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IMeleeVariantHolder<T extends ItemISMelee>
{
    T getItem();

    String[] getVariants();

    default ItemMeshDefinition getCustomMeshDefinition() { return null; };
}
