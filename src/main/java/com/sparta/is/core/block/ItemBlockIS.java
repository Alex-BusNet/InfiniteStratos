package com.sparta.is.core.block;

import cofh.core.render.FontRendererCore;
import cofh.core.util.helpers.SecurityHelper;
import com.sparta.is.core.utils.helpers.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraftforge.common.util.EnumHelper.addRarity;

public class ItemBlockIS extends ItemBlock
{
    public ItemBlockIS(Block block)
    {
        super(block);
        setHasSubtypes(false);
        setMaxDamage(0);
        setNoRepair();
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return StringHelper.localize(getUnlocalizedName(itemStack));
    }

    @Override
    public int getMetadata(int i)
    {
        return i;
    }

    @Override
    public boolean hasCustomEntity(ItemStack itemStack)
    {
        return SecurityHelper.isSecure(itemStack);
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemStack)
    {
        if(SecurityHelper.isSecure(itemStack))
        {
            location.setEntityInvulnerable(true);
            ((EntityItem)location).lifespan = Integer.MAX_VALUE;
        }

        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public FontRenderer getFontRenderer(ItemStack itemStack)
    {
        return FontRendererCore.loadFontRendererStack(itemStack);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        if(this.block instanceof ICustomRarity)
        {
            return ((ICustomRarity)this.block).getRarity(stack);
        }
        else
        {
            return ICustomRarity.FALLBACK_RARITY;
        }
    }

    public interface ICustomRarity
    {
        EnumRarity FALLBACK_RARITY = addRarity("fallback", TextFormatting.STRIKETHROUGH, "Infinite Stratos Fallback");

        EnumRarity getRarity(ItemStack stack);

    }


}
