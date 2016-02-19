package com.sparta.is.init;

import com.sparta.is.reference.Names;
import com.sparta.is.tileentity.TileEntityAlchemyArray;
import com.sparta.is.tileentity.TileEntityDummyArray;
import com.sparta.is.tileentity.TileEntityISStation;
import com.sparta.is.tileentity.TileEntityUnitStand;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityAlchemyArray.class, Names.Blocks.ALCHEMY_ARRAY);
        GameRegistry.registerTileEntity(TileEntityISStation.class, Names.Blocks.IS_UNIT_STATION);
        GameRegistry.registerTileEntity(TileEntityDummyArray.class, Names.Blocks.DUMMY_ARRAY);
        GameRegistry.registerTileEntity(TileEntityUnitStand.class, Names.Blocks.UNIT_STAND);
    }

}
