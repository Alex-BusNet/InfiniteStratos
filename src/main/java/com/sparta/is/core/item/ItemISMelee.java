package com.sparta.is.core.item;

import com.sparta.is.core.creativetab.CreativeTab;
import com.sparta.is.core.reference.Materials;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.ResourceLocationHelper;
import com.sparta.is.core.utils.interfaces.IISVariant;
import com.sparta.is.core.utils.interfaces.IMeleeVariantHolder;
import com.sparta.is.entity.driveables.types.EqualizerMeleeType;
import com.sparta.is.init.ModItems;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemISMelee extends ItemSword implements IMeleeVariantHolder<ItemISMelee>, IISVariant
{
    private String BASE_NAME;
    private final String[] VARIANTS;
    private boolean displayOnce = false;
    private EqualizerMeleeType type;
    private int activeVariant = 0;

    public ItemISMelee(String name, String ... variants)
    {
        super(Materials.Weapons.IS_SWORD);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTab.IS_TAB);
        this.setMaxStackSize(1);

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

    public int getMetadata()
    {
        return activeVariant;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> list)
    {
        if ( !getHasSubtypes() && VARIANTS.length > 0 )
        {
            for ( int meta = 0; meta < VARIANTS.length; meta++ )
            {
                list.add(new ItemStack(this, 1, meta));
            }
        }
        else
        {
            super.getSubItems(CreativeTab.IS_TAB, list);
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
        variants.put(meta, ResourceLocationHelper.getModelResourceLocation((this).getRegistryName(), suffix));
    }

    @Override
    public void registerVariants()
    {
//        TIntObjectHashMap<ModelResourceLocation> variants = new TIntObjectHashMap<>();
//        addModelVariants(variants);
//        for(int key : variants.keys())
//        {
//            ModelResourceLocation variant = variants.get(key);
//            ModelLoader.setCustomModelResourceLocation(this, key, variant);
//        }
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return CreativeTab.IS_TAB;
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
