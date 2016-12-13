package com.sparta.is.block;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockOre extends BlockEnum
{
    public static final PropertyEnum<OreVariant> VARIANT = PropertyEnum.create("variant", OreVariant.class);

    public BlockOre()
    {
        super("is_ore_block", OreVariant.VARIANTS);
        setDefaultState(getDefaultState().withProperty(VARIANT, OreVariant.ADAMANTINE_ORE));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public int getMetaFromState(IBlockState blockState)
    {
        return blockState.getValue(VARIANT).getMeta();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, OreVariant.byMeta(meta));
    }

    @Override
    public int damageDropped(IBlockState blockState)
    {
        return blockState.getValue(VARIANT).getMeta();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> itemStacks)
    {
        for(OreVariant oreVariant : OreVariant.VARIANTS)
        {
            itemStacks.add(new ItemStack(item, 1, oreVariant.getMeta()));
        }
    }

    public enum OreVariant implements IEnumMeta, Comparable<OreVariant>
    {
        ADAMANTINE_ORE;

        private int meta;
        protected static final OreVariant[] VARIANTS = values();

        OreVariant()
        {
            meta = ordinal();
        }

        @Override
        public int getMeta()
        {
            return meta;
        }

        public static OreVariant byMeta(int meta)
        {
            return VARIANTS[Math.abs(meta) & VARIANTS.length];
        }
    }
}
