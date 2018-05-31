package com.sparta.is.block;

import com.sparta.is.core.block.BlockIS;
import com.sparta.is.core.creativetab.CreativeTab;
import com.sparta.is.core.reference.UnlistedDirection;
import com.sparta.is.core.tileentity.TileEntityIS;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class BlockTileEntity extends BlockIS implements ITileEntityProvider
{
    @Nonnull
    private Class<? extends TileEntityIS> tileEntityType;

    public BlockTileEntity(String name)
    {
        this(name, Material.ROCK);
        this.setCreativeTab(CreativeTab.IS_TAB);
    }

    public BlockTileEntity(String name, Material material)
    {
        super(name, material);
        this.setCreativeTab(CreativeTab.IS_TAB);
    }

    public static final UnlistedDirection FORWARD = new UnlistedDirection("forward");
    public static final UnlistedDirection UP = new UnlistedDirection("UP");

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if(!(state instanceof IExtendedBlockState))
        {
            return state;
        }

        TileEntityIS teis = this.getTileEntity(world, pos);
        if(teis == null) { return state; }

        IExtendedBlockState extState = (IExtendedBlockState) state;
        return extState.withProperty(FORWARD, teis.getForward()).withProperty(UP, teis.getUp());
    }

    private boolean hasBlockTileEntity()
    {
        return this.tileEntityType != null;
    }

    @Nullable
    public <T extends TileEntityIS> T getTileEntity(final IBlockAccess w, final BlockPos pos)
    {
        if(!this.hasBlockTileEntity())
        {
            return null;
        }

        final TileEntity te = w.getTileEntity(pos);
        if(this.tileEntityType.isInstance(te))
        {
            return (T) te;
        }

        return null;
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{FORWARD, UP});
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    public void setTileEntity(final Class<? extends TileEntityIS> c)
    {
        this.tileEntityType = c;
        // TODO setInventory
    }

    public Class <? extends TileEntityIS> getTileEntityClass()
    {
        return this.tileEntityType;
    }

    @Nullable
    public final TileEntity createNewTileEntity(final World var1, final int var2)
    {
        if(this.hasBlockTileEntity())
        {
            try
            {
                return this.tileEntityType.newInstance();
            }
            catch ( final InstantiationException e )
            {
                throw new IllegalStateException("Failed to create a new instance of an illegal class " + this.tileEntityType, e);
            }
            catch(final IllegalAccessException e)
            {
                throw new IllegalStateException("Failed to create a new instance of " + this.tileEntityType + ", because lack of permissions", e);
            }
        }

        return null;
    }

    @Override
    public void breakBlock(final World w,  final BlockPos pos, final IBlockState state)
    {
        final TileEntityIS teis = this.getTileEntity(w, pos);
        if(teis != null)
        {
            final ArrayList<ItemStack> drops = new ArrayList<>();
            if(teis.dropItems())
            {
                teis.getDrops(w, pos, drops);
            }
            else
            {
                teis.getNoDrops(w, pos, drops);
            }

            // Spawn Drops
        }

        super.breakBlock(w, pos, state);
    }
}
