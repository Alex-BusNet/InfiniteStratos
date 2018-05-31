package com.sparta.is.core.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidIS extends Fluid
{
    public FluidIS(String fluidName, String modName)
    {
        super(fluidName, new ResourceLocation(modName, "blocks/fluid/" + fluidName + "_still"), new ResourceLocation(modName, "blocks/fluid/" + fluidName + "_flow"));
    }
}
