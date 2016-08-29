package com.sparta.is.recipe;

import com.sparta.is.api.RecipeRegistryProxy;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class RecipesFluidContainers
{
    public static void registerRecipes() {

        for (FluidContainerRegistry.FluidContainerData fluidContainerData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            if (fluidContainerData.fluid != null && fluidContainerData.filledContainer != null && fluidContainerData.emptyContainer != null) {
                RecipeRegistryProxy.addRecipe(fluidContainerData.filledContainer.copy(), fluidContainerData.fluid.copy(), fluidContainerData.emptyContainer.copy());
            }
        }
    }
}
