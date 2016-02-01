package com.sparta.is.utility;

import com.sparta.is.armor.ArmorIS;
import com.sparta.is.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class EntityHelper
{
    public static boolean isUnitEquipped(EntityLivingBase entityLivingBase, int slot)
    {
        if(entityLivingBase instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entityLivingBase;
            ItemStack item_slot = player.getCurrentArmor(slot);

            if((item_slot != null) && (item_slot.getItem() instanceof ArmorIS))
            {
                if(((ArmorIS) item_slot.getItem()).getState() == 2)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public static NBTTagCompound getCustomEntityData(Entity entity)
    {
        if (entity != null && entity.getEntityData().hasKey(Reference.LOWERCASE_MOD_ID) && entity.getEntityData().getTag(Reference.LOWERCASE_MOD_ID) instanceof NBTTagCompound)
        {

            return entity.getEntityData().getCompoundTag(Reference.LOWERCASE_MOD_ID);
        }

        return new NBTTagCompound();
    }

    public static void saveCustomEntityData(Entity entity, NBTTagCompound nbtTagCompound)
    {
        if (entity != null)
        {
            entity.getEntityData().setTag(Reference.LOWERCASE_MOD_ID, nbtTagCompound);
        }
    }

    public static void ISPlayerModifier(EntityLivingBase entity)
    {
        if(entity instanceof EntityPlayer)
        {
            ItemStack equippedArmor = ((EntityPlayer) entity).getCurrentArmor(1);

            if(equippedArmor.getItem() instanceof ArmorIS)
            {
                if(((ArmorIS) equippedArmor.getItem()).getState() == 2)
                {
                    ((EntityPlayer) entity).yOffset = 3.0F;
                    ((EntityPlayer) entity).motionX +=  0.1F;
                }
                else
                {
                    ((EntityPlayer) entity).yOffset = 1.62F;
                    ((EntityPlayer) entity).motionX = 0.1F;
                }
            }
        }
    }
}
