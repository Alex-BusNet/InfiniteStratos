package com.sparta.is.core.item;

import com.sparta.is.core.creativetab.CreativeTab;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.ResourceLocationHelper;
import com.sparta.is.core.utils.interfaces.IISVariant;
import com.sparta.is.core.utils.interfaces.IItemVariantHolder;
import com.sparta.is.entity.driveables.types.InfoType;
import com.sparta.is.init.ModItems;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIS extends Item implements IItemVariantHolder<ItemIS>, IISVariant
{
    private final String BASE_NAME;
    private final String[] VARIANTS;
    private int activeVariant = 0;
    private InfoType type;

    public ItemIS(String name, String ... variants)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTab.IS_TAB);
        this.setMaxStackSize(1);
        this.setNoRepair();

        BASE_NAME = name;

        if(variants.length == 0)
        {
            VARIANTS = new String[] {"normal"};
        }
        else
        {
            VARIANTS = variants;
        }

        ModItems.registerItem(this);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        if (hasSubtypes && itemStack.getMetadata() < VARIANTS.length)
        {
            return String.format("item.%s:%s", Reference.MOD_ID, VARIANTS[Math.abs(itemStack.getMetadata() % VARIANTS.length)]);
        }
        else
        {
            return String.format("item.%s:%s", Reference.MOD_ID, BASE_NAME);
        }
    }

    @Override
    public String getUnlocalizedName()
    {
        if (hasSubtypes && getDefaultInstance().getMetadata() < VARIANTS.length)
        {
            return String.format("item.%s:%s", Reference.MOD_ID, VARIANTS[activeVariant]);
        }
        else
        {
            return String.format("item.%s:%s", Reference.MOD_ID, BASE_NAME);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> list)
    {
        if (!getHasSubtypes() && VARIANTS.length > 0)
        {
            addSubItems(CreativeTab.IS_TAB, list);
        }
        else
        {
            super.getSubItems(CreativeTab.IS_TAB, list);
        }
    }

    protected void addSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        for ( int meta = 0; meta < VARIANTS.length; meta++ )
        {
            items.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addModelVariants(TIntObjectHashMap<ModelResourceLocation> variants)
    {
        for(String s: VARIANTS)
        {
            addVariant(variants, activeVariant++, s);
        }

        activeVariant = 0;
    }

    @Override
    public void addVariant(TIntObjectHashMap<ModelResourceLocation> variants, int meta, String suffix)
    {
        variants.put(meta, ResourceLocationHelper.getModelResourceLocation(this.getRegistryName(), suffix));
    }

    @Override
    public void registerVariants()
    {
        TIntObjectHashMap<ModelResourceLocation> variants = new TIntObjectHashMap<>();
        addModelVariants(variants);
        for(int key : variants.keys())
        {
            ModelResourceLocation variant = variants.get(key);
            ModelLoader.setCustomModelResourceLocation(this, key, variant);
        }
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return CreativeTab.IS_TAB;
    }

    @Override
    public ItemIS getItem()
    {
        return this;
    }

    @Override
    public String[] getVariants()
    {
        return VARIANTS;
    }

    public InfoType getType()
    {
        return type;
    }

}
