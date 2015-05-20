package com.sparta.is.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
    public static void init()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.yukihira), " b ", " d ", " h ", 'b', new ItemStack(ModItems.yukihiraBlade), 'd', "gemDiamond", 'h', new ItemStack(ModItems.yukihiraHilt)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.flag), new ItemStack(ModItems.yukihira), new ItemStack(ModItems.yukihira)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.yukihiraBlade), "  i", "iri", "i  ", 'i', "ingotIron", 'r', "dustRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.yukihiraHilt), "iii", " g ", "iii", 'i', "ingotIron", 'g', "ingotGold"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.byakushiki), "ipi", " c ", "idi", 'i', "ingotIron", 'p', new ItemStack(ModItems.processor), 'c', new ItemStack(ModItems.core), 'd', new ItemStack(ModItems.absDefense)));

    }
}
