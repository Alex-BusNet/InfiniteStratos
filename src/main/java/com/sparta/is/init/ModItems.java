package com.sparta.is.init;

import cofh.core.util.core.IInitializer;
import com.sparta.is.armor.UnitByakushiki;
import com.sparta.is.armor.UnitKuroAkiko;
import com.sparta.is.core.armor.ArmorIS;
import com.sparta.is.core.handler.RegistrationHandler;
import com.sparta.is.core.item.ItemIS;
import com.sparta.is.core.item.ItemISMelee;
import com.sparta.is.core.item.ItemISRange;
import com.sparta.is.core.item.ItemTabLabel;
import com.sparta.is.item.ItemAdamantine;
import com.sparta.is.item.ItemElucidator;
import com.sparta.is.item.ItemTabaneSpawnEgg;
import com.sparta.is.item.ItemYukihiraNigata;
import com.sparta.is.item.base.ItemMaterials;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ModItems
{
    public static final List<ItemIS> ITEMS = new ArrayList<>();
    public static final List<ArmorIS> UNITS = new ArrayList<>();
    public static final List<ItemISMelee> MELEE = new ArrayList<>();
    public static final List<ItemISRange> RANGE = new ArrayList<>();

    public static final RegistrationHandler HELPER = new RegistrationHandler();

    //
    // Equalizers
    //
    public static ItemISMelee yukihira = new ItemYukihiraNigata();
    public static ItemISMelee elucidator = new ItemElucidator();

    //
    // Ores
    //
    public static ItemIS adamantineItem = new ItemAdamantine();
    public static ItemMaterials itemMaterials;

//    public static ItemIS isOres = new ItemOre();

    public static ItemTabaneSpawnEgg tabaneSpawnEgg = new ItemTabaneSpawnEgg(0xFFA8DE, 0xCF5DA3);

    public static ArmorIS byakushiki = new UnitByakushiki();
    public static ArmorIS kuroakiko = new UnitKuroAkiko();

    public static Item tabLabelItem = new ItemTabLabel();

    private ModItems() {}

    public static void preInit()
    {
        itemMaterials = new ItemMaterials();

        initList.add(itemMaterials);

        for(IInitializer init : initList)
        {
            init.register();
        }
    }

    public static void init()
    {
        for(IInitializer init : initList)
        {
            init.initialize();
        }
    }

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

    public static Collection<ItemISRange> getRangeItems() { return RANGE; }

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

    private static ArrayList<IInitializer> initList = new ArrayList<>();

}
