package com.sparta.is.init;

import cofh.core.util.core.IInitializer;
import com.sparta.is.core.InfiniteStratos;
import com.sparta.is.core.fluid.BlockFluidIS;
import com.sparta.is.core.fluid.FluidIS;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.fluid.BlockFluidAdamantine;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class ModFluids
{
    public static final ModFluids INSTANCE = new ModFluids();

    private ModFluids() {}

    public static void preInit()
    {
        registerAllFluids();
        registerAllFluidBlocks();
        createBuckets();
        refreshReferences();

        for( IInitializer init : initList)
        {
            init.register();
        }
    }

    public static void registerAllFluids()
    {
        fluidAdamantine = new FluidIS("adamantine", "is").setDensity(1000).setViscosity(1500).setRarity(EnumRarity.RARE);

        FluidRegistry.registerFluid(fluidAdamantine);
    }

    public static void registerAllFluidBlocks()
    {
        blockFluidAdamantine = new BlockFluidAdamantine(fluidAdamantine);

        initList.add(blockFluidAdamantine);

        InfiniteStratos.proxy.getClientProxy().addIModel(blockFluidAdamantine);
    }

    public static void createBuckets()
    {
        FluidRegistry.addBucketForFluid(fluidAdamantine);
    }

    public static void refreshReferences()
    {
        fluidAdamantine = FluidRegistry.getFluid("adamantine");
    }

    public static void registerTextures(TextureMap map)
    {
        textureMap = map;
        registerFluidTextures(fluidAdamantine);
    }

    private static void registerFluidTextures(Fluid fluid)
    {
        registerFluidTextures(fluid.getName());
    }

    private static void registerFluidTextures(String fluidName)
    {
        textureMap.registerSprite(new ResourceLocation(Reference.MOD_ID, "blocks/fluid/" + fluidName + "_still"));
        textureMap.registerSprite(new ResourceLocation(Reference.MOD_ID, "blocks/fluid/" + fluidName + "_flow"));
    }

    public static boolean isPotion(@Nonnull FluidStack stack)
    {
        return false;
    }

    public static boolean isSplashPotion(@Nonnull FluidStack stack)
    {
        return false;
    }

    public static boolean isLingeringPotion(@Nonnull FluidStack stack)
    {
        return false;
    }

    private static ArrayList<IInitializer> initList = new ArrayList<>();
    private static TextureMap textureMap;

    public static Fluid fluidAdamantine;
    public static BlockFluidIS blockFluidAdamantine;
}
