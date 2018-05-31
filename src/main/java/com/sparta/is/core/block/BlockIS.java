package com.sparta.is.core.block;

import com.sparta.is.core.creativetab.CreativeTab;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.core.utils.helpers.ResourceLocationHelper;
import com.sparta.is.core.utils.interfaces.IISVariant;
import com.sparta.is.init.ModBlocks;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIS extends Block implements IISVariant
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
        setCreativeTab(CreativeTab.IS_TAB);
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
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return CreativeTab.IS_TAB;
    }

    @Override
    public void addVariant(TIntObjectHashMap<ModelResourceLocation> variants, int meta, String suffix)
    {
        variants.put(meta, ResourceLocationHelper.getModelResourceLocation(((Block)this).getRegistryName(), suffix));
    }

    @Override
    public void registerVariants()
    {
        Block thisItem = (Block) this;
        TIntObjectHashMap<ModelResourceLocation> variants = new TIntObjectHashMap<>();
        addModelVariants(variants);
        for(int key : variants.keys())
        {
            ModelResourceLocation variant = variants.get(key);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(thisItem), key, variant);
        }
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
