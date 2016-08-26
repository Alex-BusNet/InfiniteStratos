package com.sparta.is.block;

import com.sparta.is.tileentity.TileEntityUnitStand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockUnitStand extends BlockTileEntityIS
{
    public BlockUnitStand()
    {
        super(Material.ANVIL);
        this.setHardness(2.0f);
//        this.setBlockName(Names.Blocks.UNIT_STAND);
//        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metData)
    {
        return new TileEntityUnitStand();
    }

//    @Override
//    public boolean renderAsNormalBlock()
//    {
//        return false;
//    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

//    @Override
//    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
//    {
//        BlockPos blockPos = new BlockPos(x, y, z);
//        if (player.isSneaking())
//        {
//            return false;
//        }
//        else
//        {
//            if (!world.isRemote)
//            {
//                if (world.getTileEntity(blockPos) instanceof TileEntityUnitStand)
//                {
//                    player.openGui(InfiniteStratos.instance, GUIs.UNIT_STAND.ordinal(), world, x, y, z);
//                }
//            }
//
//            return true;
//        }
//    }

}
