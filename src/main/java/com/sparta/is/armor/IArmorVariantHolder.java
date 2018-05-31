package com.sparta.is.armor;

import com.sparta.is.core.armor.ArmorIS;
import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IArmorVariantHolder<T extends ArmorIS>
{
    T getArmor();

    String[] getVariants();

    default ItemMeshDefinition getCustomMeshDefinition() { return null; };
}
