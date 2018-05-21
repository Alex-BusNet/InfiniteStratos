package com.sparta.is.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
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
    public boolean canFit(int width, int height)
    {
        return false;
    }

//    @Override
//    public int getRecipeSize()
//    {
//        return 10;
//    }

    @Nullable
    @Override
    public ItemStack getRecipeOutput()
    {
        return null;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < itemStacks.size(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            itemStacks.set(i, ForgeHooks.getContainerItem(itemstack));
        }

        return itemStacks;
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name)
    {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName()
    {
        return null;
    }

    @Override
    public Class<IRecipe> getRegistryType()
    {
        return null;
    }
}
