package com.sparta.is.block;

import com.sparta.is.core.block.BlockIS;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.utils.interfaces.IForgeable;
import com.sparta.is.init.ModFluids;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class BlockAdamantine extends BlockIS implements IForgeable
{
//    public static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);
    public BlockAdamantine()
    {
        super(Names.Blocks.ADAMANTINE_ORE);

    }


    @Override
    public void registerBlockWithTCon()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("fluid", ModFluids.fluidAdamantine.getName());
        tag.setString("ore", Names.Blocks.ADAMANTINE_ORE);
        tag.setBoolean("toolforge", false);

        FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);
    }
}
