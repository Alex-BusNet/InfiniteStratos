package com.sparta.is.core.block;

import com.sparta.is.core.creativetab.CreativeTab;
import com.sparta.is.core.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;

public class BlockIS extends Block
{
    public String BASE_NAME;
    private String modName;
    private boolean fullSize = true;
    private boolean opaque = true;

    protected AxisAlignedBB boundingBox = FULL_BLOCK_AABB;

    public BlockIS() { this(""); }
    public BlockIS(String name)
    {
        this(name, Material.WOOD);
    }

    public BlockIS(String modName, Material material)
    {
        super(material);
        this.modName = modName;
        setCreativeTab(CreativeTab.IS_TAB);

//        ModBlocks.register(this);
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
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return CreativeTab.IS_TAB;
    }

    @Override
    public Block setUnlocalizedName(String name)
    {
        this.BASE_NAME = name;
        name = modName + ":" + name;
        return super.setUnlocalizedName(name);
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

    protected boolean setOpaque(final boolean o)
    {
        this.opaque = o;
        return opaque;
    }
}
