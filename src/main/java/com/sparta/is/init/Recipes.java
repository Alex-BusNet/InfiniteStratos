package com.sparta.is.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
    public static void init()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.yukihiraNigata), " b ", " d ", " h ", 'b', new ItemStack(ModItems.yukihiraBlade), 'd', new ItemStack(ModMaterials.isSwordMaterial), 'h', new ItemStack(ModItems.yukihiraHilt)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.flag), new ItemStack(ModItems.yukihiraNigata), new ItemStack(ModItems.yukihiraNigata)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.yukihiraBlade), "  i", "iri", "i  ", 'i', "ingotIron", 'r', "dustRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.yukihiraHilt), "iii", " g ", "iii", 'i', "ingotIron", 'g', "ingotGold"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModUnits.byakushiki), "ipi", "ici", "idi", 'i', new ItemStack(ModMaterials.isArmorMaterial), 'p', new ItemStack(ModItems.isProcessor), 'c', new ItemStack(ModItems.isCore), 'd', new ItemStack(ModItems.absoluteDefense)));

    }
}
