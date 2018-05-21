package com.sparta.is.block;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.reference.Reference;
import com.sparta.is.utils.LogHelper;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIS extends Block implements IBlockIS
{
    private final String BASE_NAME;
    private boolean fullSize = true;
    private boolean opaque = true;

    protected AxisAlignedBB boundingBox = FULL_BLOCK_AABB;

    public BlockIS() { this(""); }
    public BlockIS(String name)
    {
        this(name, Material.WOOD);
    }

    public BlockIS(String name, Material material)
    {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTab.IS_TAB);
        BASE_NAME = name;
        ModBlocks.register(this);
        this.fullBlock = this.isFullSize();
    }

    public boolean hasSubtypes()
    {
        return false;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s:%s", Reference.MOD_ID, BASE_NAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addModelVariants(TIntObjectHashMap<ModelResourceLocation> variants)
    {
        LogHelper.info("\t\t Initializing " + BASE_NAME);
        addVariant(variants, 0, "normal");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return boundingBox;
    }

    @Override
    public boolean isNormalCube(IBlockState state)
    {
        return this.isFullSize() && this.opaque;
    }

    public boolean isFullSize()
    {
        return this.fullSize;
    }

    protected boolean setFullSize(final boolean fs)
    {
        this.fullSize = fs;
        return fs;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return this.opaque;
    }

    protected boolean setOpaque(final boolean o)
    {
        this.opaque = o;
        return opaque;
    }
}
