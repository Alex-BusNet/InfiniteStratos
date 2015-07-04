package com.sparta.is.reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class Material
{
    public static final class Weapons
    {
        public static final Item.ToolMaterial IS_SWORD = EnumHelper.addToolMaterial(Names.Materials.IS_SWORD, 3, 0, 14f, 10.0f, 0);
    }

    public static final class Armor
    {
        public static final ItemArmor.ArmorMaterial IS_ARMOR = EnumHelper.addArmorMaterial(Names.Materials.IS_ARMOR, 0, new int[]{100,100,100,100}, 0);
    }

}
