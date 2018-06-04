package com.sparta.is.core.reference;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class Materials
{
    public static final class Weapons
    {
        public static final Item.ToolMaterial IS_SWORD = EnumHelper.addToolMaterial(Names.Materials.IS_SWORD, 3, 0, 14f, 10.0f, 0);
    }

    public static final class Armor
    {
        public static final ItemArmor.ArmorMaterial IS_ARMOR = EnumHelper.addArmorMaterial(Names.Materials.IS_ARMOR, Textures.Materials.IS_ARMOR.toString(), 0, new int[]{100,100,100,100}, 0, null, 100);
    }

    public static final Material adamantineMaterial = new Material(MapColor.AIR);
    public static final Material adamantineFluidMaterial = new MaterialLiquid(MapColor.AIR);
    public static final Material crystallineFluidMaterial = new MaterialLiquid(MapColor.AIR);
}
