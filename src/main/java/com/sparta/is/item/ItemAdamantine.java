package com.sparta.is.item;

import com.sparta.is.core.item.ItemIS;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.reference.Textures;
import com.sparta.is.core.utils.interfaces.IOreItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemAdamantine extends ItemIS implements IOreItem
{
    private static final String[] VARIANTS = {"normal"};
    public ItemAdamantine()
    {
        super(Names.Items.ADAMANTINE_ITEM, VARIANTS);
        setMaxStackSize(64);
    }

    @Override
    public void registerOreItem()
    {
        OreDictionary.registerOre(Names.Blocks.ADAMANTINE_ORE, this);
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
