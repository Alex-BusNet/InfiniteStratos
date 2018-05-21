package com.sparta.is.item.base;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.entity.driveables.types.InfoType;
import com.sparta.is.init.ModItems;
import com.sparta.is.reference.Reference;
import com.sparta.is.utils.ResourceLocationHelper;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIS extends Item implements IItemVariantHolder<ItemIS>, IItemIS
{
    private final String BASE_NAME;
    private final String[] VARIANTS;
    private int activeVariant = 0;
    private InfoType type;

    public ItemIS(String name, String ... variants)
    {
        super();
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.IS_TAB);
        setMaxStackSize(1);
        setNoRepair();

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
            addSubItems(creativeTab, list);
        }
        else
        {
            super.getSubItems(creativeTab, list);
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
    public ItemMeshDefinition getCustomMeshDefinition()
    {
        return itemstack -> ResourceLocationHelper.getModelResourceLocation(BASE_NAME, VARIANTS[Math.abs(activeVariant % VARIANTS.length)]);
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
