package com.sparta.is.api;

import com.sparta.is.core.InfiniteStratos;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.Collection;

public final class RecipeRegistryProxy {

    /**
     * TODO Finish JavaDoc
     *
     * @param recipeOutput
     * @param recipeInputs
     */
    public static void addRecipe(Object recipeOutput, Object ... recipeInputs) {
        addRecipe(recipeOutput, Arrays.asList(recipeInputs));
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param recipeOutput
     * @param recipeInputs
     */
    public static void addRecipe(Object recipeOutput, Collection<?> recipeInputs) {

        init();

//        if (isMod != null)
//        {
//            ISWrapper.ismod.getRecipeRegistry().addRecipe(recipeOutput, recipeInputs);
//        }
    }

    @Mod.Instance("is")
    private static Object isMod;

    private static class ISWrapper
    {
        private static InfiniteStratos ismod;
    }

    private static void init() {

        if (isMod != null) {
            ISWrapper.ismod = (InfiniteStratos) isMod;
        }
    }
}
