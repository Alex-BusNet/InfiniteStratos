package com.sparta.is.block;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.reference.GUIs;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.RenderIds;
import com.sparta.is.tileentity.TileEntityUnitStand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockUnitStand extends BlockTileEntityIS
{
    public BlockUnitStand()
    {
        super(Material.anvil);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.UNIT_STAND);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metData)
    {
        return new TileEntityUnitStand();
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
        return RenderIds.unitStand;
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
                if (world.getTileEntity(x, y, z) instanceof TileEntityUnitStand)
                {
                    player.openGui(InfiniteStratos.instance, GUIs.UNIT_STAND.ordinal(), world, x, y, z);
                }
            }

            return true;
        }
    }
}
