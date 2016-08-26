package com.sparta.is.item;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.init.ModItems;
import com.sparta.is.reference.Textures;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIS extends Item implements IItemVariantHolder<ItemIS>
{
    private final String BASE_NAME;
    private final String[] VARIANTS;

    public ItemIS(String name, String ... variants)
    {
        super();
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabIS.IS_TAB);
        setMaxStackSize(1);

        BASE_NAME = name;
        if(variants.length == 0)
        {
            VARIANTS = new String [] { "normal" };
        }
        else
        {
            VARIANTS = variants;
        }

        ModItems.ITEMS.add(this);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants()
    {
        if ( getCustomMeshDefinition() != null )
        {
            ModelLoader.setCustomMeshDefinition(this, getCustomMeshDefinition());
            for ( int i = 0; i < VARIANTS.length; i++ )
            {
                ModelBakery.registerItemVariants(this, getCustomModelResourceLocation(VARIANTS[i]));
            }
        }
        else
        {
            if ( ! getHasSubtypes() )
            {
                ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
            }
            else
            {
                for ( int i = 0;
                      i < VARIANTS.length;
                      i++ )
                {
                    ModelLoader.setCustomModelResourceLocation(this, i, getCustomModelResourceLocation(VARIANTS[i]));
                }
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

    @Override
    public ItemMeshDefinition getCustomMeshDefinition()
    {
        return null;
    }

    protected ModelResourceLocation getCustomModelResourceLocation(String variant)
    {
        return new ModelResourceLocation(InfiniteStratos.MOD_ID + ":" + variant);
    }
}
