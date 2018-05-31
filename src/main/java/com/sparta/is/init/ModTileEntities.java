package com.sparta.is.init;

import com.sparta.is.core.tileentity.TileEntityIS;
import com.sparta.is.tileentity.TileEntityISUnitStation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModTileEntities
{
    private static final List<TileEntityIS> TILE_ENTITIES = new ArrayList<>();

    public static final TileEntityIS unitStation = new TileEntityISUnitStation();

    private ModTileEntities() {}

    public static void register(TileEntityIS tileEntityIS) { TILE_ENTITIES.add(tileEntityIS); }

    public static Collection<TileEntityIS> getTileEntities() { return TILE_ENTITIES; }

}
