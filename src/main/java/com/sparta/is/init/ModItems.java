package com.sparta.is.init;

import cofh.core.util.core.IInitializer;
import com.google.common.collect.Maps;
import com.sparta.is.armor.UnitByakushiki;
import com.sparta.is.armor.UnitKuroAkiko;
import com.sparta.is.core.armor.ArmorIS;
import com.sparta.is.core.armor.IISUnit;
import com.sparta.is.core.handler.RegistrationHandler;
import com.sparta.is.core.item.ItemIS;
import com.sparta.is.core.item.ItemISMelee;
import com.sparta.is.core.item.ItemISRange;
import com.sparta.is.core.item.ItemTabLabel;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.item.ItemElucidator;
import com.sparta.is.item.ItemTabaneSpawnEgg;
import com.sparta.is.item.ItemYukihiraNigata;
import com.sparta.is.item.base.ItemMaterials;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class ModItems
{
    public static final List<ItemIS> ITEMS = new ArrayList<>();
    public static final List<ArmorIS> UNITS = new ArrayList<>();
    public static final List<ItemISMelee> MELEE = new ArrayList<>();
    public static final List<ItemISRange> RANGE = new ArrayList<>();

    private static final Map<String, IISUnit> UNIT_REGISTRY = Maps.newLinkedHashMap();


    public static final RegistrationHandler HELPER = new RegistrationHandler();

    //
    // Equalizers
    //
    public static ItemYukihiraNigata yukihira = new ItemYukihiraNigata();
    public static ItemElucidator elucidator = new ItemElucidator();

    //
    // Ores
    //
    public static ItemMaterials itemMaterials;

    //
    // IS Units
    //
    public static ArmorIS byakushiki = new UnitByakushiki();
    public static ArmorIS kuroakiko = new UnitKuroAkiko();

    //
    // Other
    //
    public static Item tabLabelItem = new ItemTabLabel();
    public static ItemTabaneSpawnEgg tabaneSpawnEgg = new ItemTabaneSpawnEgg(0xFFA8DE, 0xCF5DA3);

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

        if(UNIT_REGISTRY.containsKey(armor.identifier))
        {
            LogHelper.error("Could not register unit: " + armor.identifier + "; Unit already registered");
            return;
        }

        UNIT_REGISTRY.put(armor.identifier, armor);
    }

    public static void registerMelee(ItemISMelee melee)
    {
        MELEE.add(melee);
    }

    public static IISUnit getUnit(String identifier)
    {
        return UNIT_REGISTRY.containsKey(identifier) ? UNIT_REGISTRY.get(identifier) : null;
    }

    private static ArrayList<IInitializer> initList = new ArrayList<>();

}
