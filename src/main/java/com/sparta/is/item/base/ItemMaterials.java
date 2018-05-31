package com.sparta.is.item.base;

import cofh.core.item.ItemMulti;
import cofh.core.util.core.IInitializer;
import com.sparta.is.core.InfiniteStratos;
import com.sparta.is.core.creativetab.CreativeTab;
import net.minecraft.item.ItemStack;

import static cofh.core.util.helpers.RecipeHelper.addReverseStorageRecipe;

public class ItemMaterials extends ItemMulti implements IInitializer
{
    public ItemMaterials()
    {
        super("is");
        setUnlocalizedName("material");
        setCreativeTab(CreativeTab.IS_TAB);
    }

    @Override
    public boolean register()
    {
//        ForgeRegistries.ITEMS.register(setRegistryName("material"));
        InfiniteStratos.proxy.getClientProxy().addIModel(this);

        adamantineIngot = addOreDictItem(2000, "ingotAdamantine");
        armorMaterial = addOreDictItem(2001, "ingotArmorMaterial");
        weaponMaterial = addOreDictItem(2002, "ingotWeaponMaterial");

        return true;
    }

    @Override
    public boolean initialize()
    {
        addReverseStorageRecipe(adamantineIngot, "blockAdamantine");

        return true;
    }

    public static ItemStack adamantineIngot;
    public static ItemStack armorMaterial;
    public static ItemStack weaponMaterial;
}
