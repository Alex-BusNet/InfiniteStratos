package com.sparta.is.init;

import com.sparta.is.armor.ArmorByakushikiChest;
import com.sparta.is.armor.ArmorIS;
import com.sparta.is.armor.UnitByakushiki;
import com.sparta.is.armor.UnitKuroAkiko;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModUnits
{
    public static final ArmorIS byakushikiChest = new ArmorByakushikiChest();
    public static final ArmorIS byakushiki = new UnitByakushiki();
    public static final ArmorIS kuroAkiko = new UnitKuroAkiko();

    public static void init()
    {
        GameRegistry.registerItem(byakushikiChest, Names.Parts.BYAKUSHIKI_CHEST);
        GameRegistry.registerItem(byakushiki, Names.Units.BYAKUSHIKI);
        GameRegistry.registerItem(kuroAkiko, Names.Units.KURO_AKIKO);
    }
}
