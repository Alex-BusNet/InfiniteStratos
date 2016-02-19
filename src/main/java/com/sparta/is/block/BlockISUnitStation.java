package com.sparta.is.block;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.init.ModItems;
import com.sparta.is.reference.GUIs;
import com.sparta.is.reference.RenderIds;
import com.sparta.is.tileentity.TileEntityISStation;
import com.sparta.is.tileentity.TileEntityUnitStand;
import com.sparta.is.utility.LogHelper;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockISUnitStation extends BlockTileEntityIS
{

    protected Item storedUnit;
    public BlockISUnitStation()
    {
        super(Material.anvil);
        this.setHardness(2.0f);
//        this.setBlockName(Names.Blocks.IS_UNIT_STATION);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metData)
    {
        return new TileEntityISStation();
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
    public boolean onBlockActivated(World world, BlockPos blockPos, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (world.getTileEntity(blockPos) instanceof TileEntityISStation)
                {
//                    LogHelper.info("Orientation: " + ((TileEntityISStation) world.getTileEntity(blockPos)).getOrientation());
                    if(findStand(((TileEntityISStation) world.getTileEntity(blockPos)).getOrientation(), world, blockPos))
                    {
                        player.openGui(InfiniteStratos.instance, GUIs.IS_UNIT_STATION.ordinal(), world, blockPos);
                    }
                }
            }

            return true;
        }
    }

    protected boolean findStand(ForgeDirection direction, World station, BlockPos blockPos)
    {
        switch(direction)
        {
            case NORTH:
                if(station.getBlock(blockPos.add(0, 0, 2)) == ModBlocks.unitStand)
                {
                    if(((TileEntityUnitStand)station.getTileEntity(blockPos.add(0, 0, 2))).getStackInSlot(0) != null)
                    {
                        if(!((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby())
                        {
                            ((TileEntityISStation) station.getTileEntity(blockPos)).setStandNearby();
                        }

                        LogHelper.info("Unit stand nearby and contains unit");
                    }

                    LogHelper.info("isStandNearby:" + ((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby());

                    if(((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby())
                    {
                        storedUnit = ((TileEntityUnitStand) station.getTileEntity(blockPos.add(0, 0, 2))).getStackInSlot(0).getItem();
                        LogHelper.info("Unit in Stand" + storedUnit.getUnlocalizedName());
                    }
                    else
                    {
                        storedUnit = ModItems.testUnit;
                    }

                    ((TileEntityISStation) station.getTileEntity(blockPos)).setStoredUnit(storedUnit);
                    return true;
                }
                break;
            case SOUTH:
                if(station.getBlock(blockPos.add(0, 0, - 2)) == ModBlocks.unitStand)
                {
                    if(((TileEntityUnitStand)station.getTileEntity(blockPos.add(0, 0, -2))).getStackInSlot(0) != null)
                    {
                        if(!((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby())
                        {
                            ((TileEntityISStation) station.getTileEntity(blockPos)).setStandNearby();
                        }

                        LogHelper.info("Unit stand nearby and contains unit");
                    }

                    LogHelper.info("isStandNearby:" + ((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby());

                    if(((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby())
                    {
                        storedUnit = ((TileEntityUnitStand) station.getTileEntity(blockPos.add(0, 0, -2))).getStackInSlot(0).getItem();
                        LogHelper.info("Unit in Stand" + storedUnit.getUnlocalizedName());
                    }
                    else
                    {
                        storedUnit = ModItems.testUnit;
                    }

                    ((TileEntityISStation) station.getTileEntity(blockPos)).setStoredUnit(storedUnit);
                    return true;
                }
                break;
            case EAST:
                if(station.getBlock(blockPos.add(-2, 0, 0)) == ModBlocks.unitStand)
                {
                    if(((TileEntityUnitStand)station.getTileEntity(blockPos.add(-2, 0, 0))).getStackInSlot(0) != null)
                    {
                        if(!((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby())
                        {
                            ((TileEntityISStation) station.getTileEntity(blockPos)).setStandNearby();
                        }

                        LogHelper.info("Unit stand nearby and contains unit");
                    }

                    LogHelper.info("isStandNearby:" + ((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby());

                    if(((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby())
                    {
                        storedUnit = ((TileEntityUnitStand) station.getTileEntity(blockPos.add(-2, 0, 0))).getStackInSlot(0).getItem();
                        LogHelper.info("Unit in Stand" + storedUnit.getUnlocalizedName());
                    }
                    else
                    {
                        storedUnit = ModItems.testUnit;
                    }

                    ((TileEntityISStation) station.getTileEntity(blockPos)).setStoredUnit(storedUnit);
                    return true;
                }
                break;
            case WEST:
                if(station.getBlock(blockPos.add(2, 0, 0)) == ModBlocks.unitStand)
                {
                    if(((TileEntityUnitStand)station.getTileEntity(blockPos.add(2, 0, 0))).getStackInSlot(0) != null)
                    {
                        if(!((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby())
                        {
                            ((TileEntityISStation) station.getTileEntity(blockPos)).setStandNearby();
                        }

                        LogHelper.info("Unit stand nearby and contains unit");
                    }

                    LogHelper.info("isStandNearby:" + ((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby());

                    if(((TileEntityISStation) station.getTileEntity(blockPos)).isStandNearby())
                    {
                        storedUnit = ((TileEntityUnitStand) station.getTileEntity(blockPos.add(2, 0, 0))).getStackInSlot(0).getItem();
                        LogHelper.info("Unit in Stand" + storedUnit.getUnlocalizedName());
                    }
                    else
                    {
                        storedUnit = ModItems.testUnit;
                    }

                    ((TileEntityISStation) station.getTileEntity(blockPos)).setStoredUnit(storedUnit);
                    return true;
                }
                break;
            default:
                break;
        }
        ((TileEntityISStation) station.getTileEntity(blockPos)).setStandNearby();
        return false;
    }
}
