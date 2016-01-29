package com.sparta.is.armor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Textures;
import com.sparta.is.utility.IModifyable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ArmorIS extends ItemArmor implements IModifyable
{
    public String repairIngot = "";
    public String[] textures = new String[2];
    protected Multimap<String, AttributeModifier> properties = HashMultimap.create();
    protected boolean showInCreative = true;
    protected final String modifyType;
    private static int state = 0;

    private static int totalShieldCapacity;
    private static int remainingShieldCapacity;

    public ArmorIS(ArmorMaterial armorMaterial, int slot, String type)
    {
        super(armorMaterial, 0 , slot);
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.setMaxStackSize(1);
        this.modifyType = type;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("armor.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("armor.%s%s",Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedLocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public ArmorIS setArmorTextures(String[] textures)
    {
        this.textures = textures;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type)
    {
        if(textures[1] !=  null)
        {
            return textures[1];
        }
        return textures[0];
    }

    public int getShieldCapacity()
    {
        return totalShieldCapacity;
    }

    public void setShieldCapacity(int capacity)
    {
        totalShieldCapacity = capacity;
    }

    public void lowerShieldEnergy(ItemStack itemStack)
    {
        remainingShieldCapacity -= 10;
    }

    public int getRemainingShieldCapacity()
    {
        return remainingShieldCapacity;
    }

    public void setRemainingShieldCapacity()
    {
        remainingShieldCapacity = totalShieldCapacity;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int newState)
    {
        state = newState;
    }


    @Override
    public String getBaseTagName()
    {
        return "ISUnit";
    }

    @Override
    public String getModifyType()
    {
        return modifyType;
    }

    @Override
    public String[] getTraits()
    {
        return new String[] {"armor"};
    }



}
