package com.sparta.is.init;

import com.sparta.is.item.*;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemSword yukihira = new ItemYukihira();
    public static final ItemISUnit byakushiki = new ItemArmorByakushiki();
    public static final ItemISWeaponBase yukihiraBlade = new ItemYukihiraBlade();
    public static final ItemISWeaponBase yukihiraHilt = new ItemYukihiraHilt();
    public static final Item core = new ItemISCore();
    public static final ItemIS processor = new ItemISProcessor();
    public static final ItemIS absDefense = new ItemISDefense();

    public static void init()
    {
        GameRegistry.registerItem(yukihira, Names.Weapons.YUKIHIRA_NIGATA);
        GameRegistry.registerItem(byakushiki, Names.Armor.BYAKUSHIKI);
        GameRegistry.registerItem(yukihiraBlade, Names.Parts.YUKIHIRA_BLADE);
        GameRegistry.registerItem(yukihiraHilt, Names.Parts.YUKIHIRA_HILT);
        GameRegistry.registerItem(core, Names.Parts.IS_CORE);
        GameRegistry.registerItem(processor, Names.Parts.IS_PROCESSOR);
        GameRegistry.registerItem(absDefense, Names.Parts.ABSOLUTE_DEFENSE);

    }
}
