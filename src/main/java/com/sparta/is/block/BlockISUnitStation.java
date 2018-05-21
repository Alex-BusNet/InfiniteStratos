package com.sparta.is.block;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.armor.ArmorIS;
import com.sparta.is.reference.GUIs;
import com.sparta.is.reference.Names;
import com.sparta.is.tileentity.TileEntityISUnitStation;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockISUnitStation extends BlockTileEntity
{
    protected ArmorIS storedUnit;

    public BlockISUnitStation()
    {
        super(Names.Blocks.IS_UNIT_STATION, Material.IRON);
        this.setHardness(2.0f);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if ( ! world.isRemote )
            {
                if ( world.getTileEntity(pos) instanceof TileEntityISUnitStation )
                {
                    player.openGui(InfiniteStratos.instance, GUIs.IS_UNIT_STATION.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
                }
            }

            return true;
        }
    }
}
