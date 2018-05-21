package com.sparta.is.init;

import com.sparta.is.armor.ArmorIS;
import com.sparta.is.armor.UnitByakushiki;
import com.sparta.is.armor.UnitKuroAkiko;
import com.sparta.is.item.ItemAdamantine;
import com.sparta.is.item.ItemElucidator;
import com.sparta.is.item.ItemTabaneSpawnEgg;
import com.sparta.is.item.ItemYukihiraNigata;
import com.sparta.is.item.base.ItemIS;
import com.sparta.is.item.base.ItemISMelee;
import net.minecraft.item.ItemEgg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModItems
{
    public static final List<ItemIS> ITEMS = new ArrayList<>();
    public static final List<ArmorIS> UNITS = new ArrayList<>();
    public static final List<ItemISMelee> MELEE = new ArrayList<>();
//    public static final List<ItemISRange> RANGE = new ArrayList<>();

    public static ItemISMelee yukihira = new ItemYukihiraNigata();
    public static ItemISMelee elucidator = new ItemElucidator();
    public static ItemIS adamantineItem = new ItemAdamantine();
    public static ItemEgg tabaneSpawnEgg = new ItemTabaneSpawnEgg(0xFFA8DE, 0xCF5DA3);

    public static ArmorIS byakushiki = new UnitByakushiki();
    public static ArmorIS kuroakiko = new UnitKuroAkiko();

    private ModItems() {}

    public static Collection<ItemIS> getItems()
    {
        return ITEMS;
    }

    public static Collection<ItemISMelee> getMeleeItems()
    {
        return MELEE;
    }

    public static Collection<ArmorIS> getUnits()
    {
        return UNITS;
    }

    public static void registerItem(ItemIS item)
    {
        ITEMS.add(item);
    }

    public static void registerArmor(ArmorIS armor)
    {
        UNITS.add(armor);
    }

    public static void registerMelee(ItemISMelee melee)
    {
        MELEE.add(melee);
    }

}
