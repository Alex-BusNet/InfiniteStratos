package com.sparta.is.item.base;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.entity.driveables.types.EqualizerMeleeType;
import com.sparta.is.init.ModItems;
import com.sparta.is.reference.Materials;
import com.sparta.is.reference.Reference;
import com.sparta.is.utils.ResourceLocationHelper;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ItemISMelee extends ItemSword implements IMeleeVariantHolder<ItemISMelee>
{
    private String BASE_NAME;
    private final String[] VARIANTS;
    private boolean displayOnce = false;
    private EqualizerMeleeType type;

    public ItemISMelee(String name, String ... variants)
    {
        super(Materials.Weapons.IS_SWORD);
        setRegistryName(name);
        setCreativeTab(CreativeTab.IS_TAB);
        setMaxStackSize(1);
        BASE_NAME = name;

        if(variants.length == 0)
        {
            VARIANTS = new String[] {"normal"};
        }
        else
        {
            VARIANTS = variants;
        }

        ModItems.registerMelee(this);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("weapon.%s:%s", Reference.MOD_ID, BASE_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("weapon.%s:%s", Reference.MOD_ID, BASE_NAME);
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
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
    public ItemISMelee getItem()
    {
        return this;
    }

    @Override
    public String[] getVariants()
    {
        return VARIANTS;
    }

    public EqualizerMeleeType getType()
    {
        return type;
    }

}
