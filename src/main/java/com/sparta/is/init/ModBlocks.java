package com.sparta.is.init;

import cofh.core.util.core.IInitializer;
import com.sparta.is.block.BlockISUnitStation;
import com.sparta.is.block.BlockItemDisplay;
import com.sparta.is.block.BlockOre;
import com.sparta.is.core.block.BlockIS;
import com.sparta.is.core.block.BlockISContainerBase;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModBlocks
{
    private static final List<BlockIS> BLOCKS = new ArrayList<>();
    private static final List<BlockISContainerBase> TILE_BLOCKS = new ArrayList<>();
    private static final ArrayList<IInitializer> initList = new ArrayList<>();

    public static BlockOre oreBlock;
    public static BlockISUnitStation unitStation;
    public static BlockItemDisplay itemDisplay;

    private ModBlocks() {}

    public static void preInit()
    {
        oreBlock = new BlockOre();
        itemDisplay = new BlockItemDisplay();
        unitStation = new BlockISUnitStation();

        initList.add(oreBlock);
        initList.add(itemDisplay);
        initList.add(unitStation);

        for( IInitializer init : initList)
        {
            init.register();
        }
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        for(IInitializer init : initList)
        {
            init.initialize();
        }
    }

    public static void register(BlockIS block)
    {
        BLOCKS.add(block);
    }

    public static Collection<BlockIS> getBlocks()
    {
        return BLOCKS;
    }

    public static void registerTileEntity(BlockISContainerBase block)
    {
        TILE_BLOCKS.add(block);
    }

    public static Collection<BlockISContainerBase> getTileBlocks()
    {
        return TILE_BLOCKS;
    }
}
