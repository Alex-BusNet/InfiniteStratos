package com.sparta.is.item;

import com.sparta.is.item.base.ItemIS;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Textures;
import net.minecraft.item.ItemStack;

public class ItemAdamantine extends ItemIS
{
    private static final String[] VARIANTS = {"normal"};
    public ItemAdamantine()
    {
        super(Names.Items.ADAMANTINE_ITEM, VARIANTS);
        setMaxStackSize(64);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, Names.Items.ADAMANTINE_ITEM);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, Names.Items.ADAMANTINE_ITEM);
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
