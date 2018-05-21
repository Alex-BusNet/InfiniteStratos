package com.sparta.is.item.base;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.entity.driveables.types.EqualizerMeleeType;
import com.sparta.is.init.ModItems;
import com.sparta.is.reference.Materials;
import com.sparta.is.reference.Reference;
import com.sparta.is.utils.ResourceLocationHelper;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemISMelee extends ItemSword implements IMeleeVariantHolder<ItemISMelee>, IItemIS
{
    private String BASE_NAME;
    private final String[] VARIANTS;
    private boolean displayOnce = false;
    private EqualizerMeleeType type;
    private int activeVariant = 0;

    public ItemISMelee(String name, String ... variants)
    {
        super(Materials.Weapons.IS_SWORD);
        setRegistryName(name);
        setUnlocalizedName(name);
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

    @Override
    public ItemMeshDefinition getCustomMeshDefinition()
    {
        return itemstack -> ResourceLocationHelper.getModelResourceLocation(BASE_NAME, VARIANTS[Math.abs(activeVariant % VARIANTS.length)]);
    }

    public int getMetadata()
    {
        return activeVariant;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> list)
    {
        if ( ! getHasSubtypes() && VARIANTS.length > 0 )
        {
            for ( int meta = 0; meta < VARIANTS.length; meta++ )
            {
                list.add(new ItemStack(this, 1, meta));
            }
        }
        else
        {
            super.getSubItems(creativeTab, list);
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
