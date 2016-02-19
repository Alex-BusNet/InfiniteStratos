package com.sparta.is.init;

import com.sparta.is.item.ItemISArmorMaterial;
import com.sparta.is.item.ItemISSwordMaterial;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModMaterials
{
    public static ItemISArmorMaterial isArmorMaterial = new ItemISArmorMaterial();
    public static ItemISSwordMaterial isSwordMaterial = new ItemISSwordMaterial();

    public static void init()
    {
        GameRegistry.registerItem(isArmorMaterial, Names.Materials.IS_ARMOR);
        GameRegistry.registerItem(isSwordMaterial, Names.Materials.IS_SWORD);
    }

}
