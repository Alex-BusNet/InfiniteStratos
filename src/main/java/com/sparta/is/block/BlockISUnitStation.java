package com.sparta.is.block;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.reference.GUIs;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.RenderIds;
import com.sparta.is.tileentity.TileEntityISStation;
import com.sparta.is.utility.LogHelper;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
            if (!world.isRemote)
            {
                if (world.getTileEntity(x, y, z) instanceof TileEntityISStation)
                {
                    LogHelper.info("Orientation: " + ((TileEntityISStation) world.getTileEntity(x, y, z)).getOrientation());
                    if(findStand(((TileEntityISStation) world.getTileEntity(x,y,z)).getOrientation(), world, x, y, z))
                    {
                        player.openGui(InfiniteStratos.instance, GUIs.IS_UNIT_STATION.ordinal(), world, x, y, z);
                    }
                }
            }

            return true;
        }
    }

    protected boolean findStand(ForgeDirection direction, World station, int x, int y, int z)
    {
        switch(direction)
        {
            case NORTH:
                if(station.getBlock(x, y, z + 2) == ModBlocks.unitStand)
                {
                    return true;
                }
                break;
            case SOUTH:
                if(station.getBlock(x, y, z - 2) == ModBlocks.unitStand)
                {
                    return true;
                }
                break;
            case EAST:
                if(station.getBlock(x - 2, y, z) == ModBlocks.unitStand)
                {
                    return true;
                }
                break;
            case WEST:
                if(station.getBlock(x + 2, y, z) == ModBlocks.unitStand)
                {
                    return true;
                }
                break;
            default:
                break;
        }

        return false;
    }
}
