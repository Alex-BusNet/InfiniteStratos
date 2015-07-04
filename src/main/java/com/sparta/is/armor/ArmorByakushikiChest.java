package com.sparta.is.armor;

import com.sparta.is.reference.Material;
import com.sparta.is.reference.Names;
import com.sparta.repackage.cofh.api.energy.IEnergyContainerItem;
import com.sparta.repackage.cofh.api.item.IEmpowerableItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

public class ArmorByakushikiChest extends ArmorIS implements IEmpowerableItem, IEnergyContainerItem, ISpecialArmor
{
    public ArmorByakushikiChest()
    {
        super(Material.Armor.IS_ARMOR, 1, "Armor");
        this.setUnlocalizedName(Names.Parts.BYAKUSHIKI_CHEST);
    }

    @Override
    public boolean isEmpowered(ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean setEmpoweredState(ItemStack stack, boolean state)
    {
        return false;
    }

    @Override
    public void onStateChange(EntityPlayer player, ItemStack stack)
    {

    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
    {
        return 0;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
    {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack container)
    {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ItemStack container)
    {
        return 0;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        return null;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {

    }
}
