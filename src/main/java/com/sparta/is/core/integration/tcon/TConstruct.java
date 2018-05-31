package com.sparta.is.core.integration.tcon;

import com.sparta.is.core.integration.PluginISBase;
import com.sparta.is.core.utils.helpers.StringHelper;
import net.minecraft.item.EnumRarity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import java.util.List;

public class TConstruct extends PluginISBase
{
    public static final String MOD_ID = "tconstruct";
    public static final String MOD_NAME = "Tinker's Construct";

    public TConstruct()
    {
        super(MOD_ID, MOD_NAME);
    }

    public void initializeDelegate()
    {
        addFluid("adamantine", 0x2e6bf0, 1400, EnumRarity.RARE);
    }

    /* HELPERS */
    protected static void addFluid(String fluidName, int color, int temperature, EnumRarity rarity) {

        Fluid fluid = new FluidMolten(fluidName).setColor(color).setTemperature(temperature).setRarity(rarity);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);

        NBTTagCompound message = new NBTTagCompound();
        message.setString("fluid", fluid.getName());
        message.setString("ore", StringHelper.titleCase(fluidName));
        message.setBoolean("toolforge", false);

        FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", message);
    }

    protected static void addFluid(String fluidName, int color, int temperature) {

        addFluid(fluidName, color, temperature, EnumRarity.COMMON);
    }

    protected static void addAlloy(List<FluidStack> fluidStacks) {

        NBTTagList tagList = new NBTTagList();

        for (FluidStack stack : fluidStacks) {
            tagList.appendTag(stack.writeToNBT(new NBTTagCompound()));
        }
        NBTTagCompound message = new NBTTagCompound();
        message.setTag("alloy", tagList);
        FMLInterModComms.sendMessage("tconstruct", "alloy", message);
    }

    public static class FluidMolten extends Fluid
    {

        public int color = 0xFFFFFF;

        public FluidMolten(String fluidName) {

            super(fluidName, new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow"));
        }

        public FluidMolten setColor(int color) {

            this.color = 0xFF000000 | color;
            return this;
        }

        @Override
        public int getColor() {

            return color;
        }

    }
}
