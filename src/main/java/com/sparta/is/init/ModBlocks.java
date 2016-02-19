package com.sparta.is.init;

import com.sparta.is.block.*;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockIS flag = new BlockFlag();
    public static final BlockTileEntityIS isStation = new BlockISUnitStation();
    public static final BlockTileEntityIS alchemyArray = new BlockISAlchemyArray();
    public static final BlockTileEntityIS unitStand = new BlockUnitStand();

    public static void init()
    {
        GameRegistry.registerBlock(flag, Names.Blocks.FLAG);
        GameRegistry.registerBlock(isStation, Names.Blocks.IS_UNIT_STATION);
        GameRegistry.registerBlock(alchemyArray, Names.Blocks.ALCHEMY_ARRAY);
        GameRegistry.registerBlock(unitStand, Names.Blocks.UNIT_STAND);
    }
}
