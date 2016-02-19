package com.sparta.is.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.reference.Reference;
import net.minecraft.item.ItemStack;

public class NEIConfig implements IConfigureNEI
{

    @Override
    public void loadConfig()
    {
        API.hideItem(new ItemStack(ModBlocks.alchemyArray));
    }

    @Override
    public String getName()
    {
        return Reference.MOD_NAME;
    }

    @Override
    public String getVersion()
    {
        return Reference.MOD_VERSION;
    }
}
