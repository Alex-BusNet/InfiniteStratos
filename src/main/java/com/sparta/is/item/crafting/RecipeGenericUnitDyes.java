package com.sparta.is.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class RecipeGenericUnitDyes implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        ItemStack itemStack = null;
        ArrayList<ItemStack> arrayList = new ArrayList<>();

        for(int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack currentStack = inv.getStackInSlot(i);

            if(currentStack != null)
            {

            }
        }

        return itemStack != null && !arrayList.isEmpty();
    }

    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        return null;
    }

    @Override
    public int getRecipeSize()
    {
        return 10;
    }

    @Nullable
    @Override
    public ItemStack getRecipeOutput()
    {
        return null;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv)
    {
        ItemStack[] itemStacks = new ItemStack[inv.getSizeInventory()];

        for (int i = 0; i < itemStacks.length; ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            itemStacks[i] = ForgeHooks.getContainerItem(itemstack);
        }

        return itemStacks;
    }
}
