package com.sparta.is.block;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.reference.GUIs;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.RenderIds;
import com.sparta.is.tileentity.TileEntityISStation;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockISUnitStation extends BlockTileEntityIS
{
    public BlockISUnitStation()
    {
        super(Material.anvil);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.IS_UNIT_STATION);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metData)
    {
        return new TileEntityISStation();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderIds.isUnitStation;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote && (world.getBlock(x, y, z - 2) == ModBlocks.unitStand))
            {
                if (world.getTileEntity(x, y, z) instanceof TileEntityISStation)
                {
                    player.openGui(InfiniteStratos.instance, GUIs.IS_UNIT_STATION.ordinal(), world, x, y, z);
                }
            }

            return true;
        }
    }

}