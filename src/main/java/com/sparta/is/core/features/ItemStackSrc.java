package com.sparta.is.core.features;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemStackSrc implements IStackSrc
{
    public final Item item;
    public final Block block;
    public final int damage;

    public ItemStackSrc( Item i, int dmg )
    {
        this.block = null;
        this.item = i;
        this.damage = dmg;
    }

    public ItemStackSrc( Block b, int dmg )
    {
        this.item = null;
        this.block = b;
        this.damage = dmg;
    }

    @Nullable
    @Override
    public ItemStack stack( int i )
    {
        if( this.block != null )
        {
            return new ItemStack( this.block, i, this.damage );
        }

        if( this.item != null )
        {
            return new ItemStack( this.item, i, this.damage );
        }

        return null;
    }

    @Override
    public Item getItem()
    {
        return this.item;
    }

    @Override
    public int getDamage()
    {
        return this.damage;
    }
}
