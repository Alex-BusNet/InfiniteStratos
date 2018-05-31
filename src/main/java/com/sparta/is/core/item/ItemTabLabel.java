package com.sparta.is.core.item;

import com.sparta.is.core.reference.Names;
import com.sparta.is.core.reference.Textures;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTabLabel extends Item
{
    public ItemTabLabel()
    {
        super();
        setRegistryName(Names.Empty.TAB_LABEL);
        setMaxStackSize(0);
        this.setUnlocalizedName(Names.Empty.TAB_LABEL);
//        this.setTextureName(Names.Empty.TAB_LABEL);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
