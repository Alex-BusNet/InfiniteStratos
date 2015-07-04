package com.sparta.is.init;

import com.sparta.is.item.*;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemISEqualizer yukihira = new ItemYukihira();
    public static final ItemISEqualizer elucidator = new ItemElucidator();

    public static final ItemIS yukihiraBlade = new ItemYukihiraBlade();
    public static final ItemIS yukihiraHilt = new ItemYukihiraHilt();

    public static final Item core = new ItemISCore();
    public static final ItemIS processor = new ItemISProcessor();
    public static final ItemIS absDefense = new ItemISDefense();

    public static final Item tabLabel = new ItemTabLabel();

    public static final ItemISUnit testUnit = new ItemISTestUnit();

    public static final ItemTabaneSpawnEgg tabaneSpawnEgg = new ItemTabaneSpawnEgg(0xFFA8DE, 0xCF5DA3);

    public static void init()
    {
        GameRegistry.registerItem(yukihira, Names.Weapons.YUKIHIRA_NIGATA);
        GameRegistry.registerItem(yukihiraBlade, Names.Parts.YUKIHIRA_BLADE);
        GameRegistry.registerItem(yukihiraHilt, Names.Parts.YUKIHIRA_HILT);
        GameRegistry.registerItem(core, Names.Parts.IS_CORE);
        GameRegistry.registerItem(processor, Names.Parts.IS_PROCESSOR);
        GameRegistry.registerItem(absDefense, Names.Parts.ABSOLUTE_DEFENSE);
        GameRegistry.registerItem(testUnit, Names.Units.TEST_UNIT);
        GameRegistry.registerItem(tabaneSpawnEgg, Names.Villagers.TABANE);
        GameRegistry.registerItem(elucidator, Names.Weapons.ELUCIDATOR);
        GameRegistry.registerItem(tabLabel, Names.Empty.TAB_LABEL);
    }


}
