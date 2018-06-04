package com.sparta.is.core.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;

public class ItemUtils
{
    public static Item getItemFromName(String name){
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(name));
    }


    public static boolean contains(ItemStack[] array, ItemStack stack, boolean checkWildcard){
        return getPlaceAt(array, stack, checkWildcard) != -1;
    }

    public static int getPlaceAt(ItemStack[] array, ItemStack stack, boolean checkWildcard){
        return getPlaceAt(Arrays.asList(array), stack, checkWildcard);
    }

    public static int getPlaceAt(List<ItemStack> list, ItemStack stack, boolean checkWildcard){
        if(list != null && list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                if((!ItemStackUtils.isValid(stack) && !ItemStackUtils.isValid(list.get(i))) || areItemsEqual(stack, list.get(i), checkWildcard)){
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean areItemsEqual(ItemStack stack1, ItemStack stack2, boolean checkWildcard){
        return ItemStackUtils.isValid(stack1) && ItemStackUtils.isValid(stack2) && (stack1.isItemEqual(stack2) || (checkWildcard && stack1.getItem() == stack2.getItem() && (stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE)));
    }

    /**
     * Returns true if list contains stack or if both contain null
     */
    public static boolean contains(List<ItemStack> list, ItemStack stack, boolean checkWildcard){
        return !(list == null || list.isEmpty()) && getPlaceAt(list, stack, checkWildcard) != -1;
    }

    public static void addEnchantment(ItemStack stack, Enchantment e, int level){
        if(!hasEnchantment(stack, e)){
            stack.addEnchantment(e, level);
        }
    }

    public static boolean hasEnchantment(ItemStack stack, Enchantment e){
        NBTTagList ench = stack.getEnchantmentTagList();
        if(ench != null){
            for(int i = 0; i < ench.tagCount(); i++){
                short id = ench.getCompoundTagAt(i).getShort("id");
                if(id == Enchantment.getEnchantmentID(e)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void removeEnchantment(ItemStack stack, Enchantment e){
        NBTTagList ench = stack.getEnchantmentTagList();
        if(ench != null){
            for(int i = 0; i < ench.tagCount(); i++){
                short id = ench.getCompoundTagAt(i).getShort("id");
                if(id == Enchantment.getEnchantmentID(e)){
                    ench.removeTag(i);
                }
            }
            if(ench.hasNoTags() && stack.hasTagCompound()){
                stack.getTagCompound().removeTag("ench");
            }
        }
    }

    public static boolean canBeStacked(ItemStack stack1, ItemStack stack2){
        return ItemStack.areItemsEqual(stack1, stack2) && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }

    public static boolean isEnabled(ItemStack stack){
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("IsEnabled");
    }

    public static void changeEnabled(EntityPlayer player, EnumHand hand){
        changeEnabled(player.getHeldItem(hand));
    }

    public static void changeEnabled(ItemStack stack){
        if(!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }

        boolean isEnabled = isEnabled(stack);
        stack.getTagCompound().setBoolean("IsEnabled", !isEnabled);
    }
}
