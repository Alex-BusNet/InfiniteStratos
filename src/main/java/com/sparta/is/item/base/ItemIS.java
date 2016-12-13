package com.sparta.is.item.base;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.init.ModItems;
import com.sparta.is.reference.Reference;
import com.sparta.is.utils.ResourceLocationHelper;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ItemIS extends Item implements IItemVariantHolder<ItemIS>
{
    private final String BASE_NAME;
    private final String[] VARIANTS;

    public ItemIS(String name, String ... variants)
    {
        super();
        setRegistryName(name);
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
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, NonNullList<ItemStack> list)
    {

        if (!getHasSubtypes() && VARIANTS.length > 0)
        {
            for(int meta = 0; meta < VARIANTS.length; meta++)
            {
                list.add(new ItemStack(this, 1, meta));
            }
        }
        else
        {
            super.getSubItems(item, creativeTab, list);
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants()
    {
        if (getCustomMeshDefinition() != null)
        {
            for (int i = 0; i < VARIANTS.length; i++)
            {
                ModelBakery.registerItemVariants(this, ResourceLocationHelper.getModelResourceLocation(VARIANTS[i]));
            }

            ModelLoader.setCustomMeshDefinition(this, getCustomMeshDefinition());
        }
        else
        {
            if (getHasSubtypes() && VARIANTS.length > 0)
            {
                List<ModelResourceLocation> modelResources = new ArrayList<>();
                for(int i = 0; i < VARIANTS.length; i++)
                {
                    modelResources.add(ResourceLocationHelper.getModelResourceLocation(VARIANTS[i]));
                }

                ModelBakery.registerItemVariants(this, modelResources.toArray(new ModelResourceLocation[0]));
                ModelLoader.setCustomMeshDefinition(this, itemStack -> modelResources.get(itemStack.getMetadata()));
            }
            else
            {
                    ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
            }
        }
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
}
