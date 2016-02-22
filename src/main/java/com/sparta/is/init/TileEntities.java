package com.sparta.is.init;

import com.sparta.is.reference.Names;
import com.sparta.is.tileentity.TileEntityISStation;
import com.sparta.is.tileentity.TileEntityUnitStand;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityISStation.class, Names.Blocks.IS_UNIT_STATION);
        GameRegistry.registerTileEntity(TileEntityUnitStand.class, Names.Blocks.UNIT_STAND);
    }

}
