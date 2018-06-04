package com.sparta.is.core.utils;

import com.sparta.is.core.reference.Comparators;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.core.utils.interfaces.IDisableableItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ItemStackUtils
{
    public static ItemStack clone(ItemStack itemStack, int stackSize) {

        if (itemStack != null) {
            ItemStack clonedItemStack = itemStack.copy();
            clonedItemStack.setCount(stackSize);
            return clonedItemStack;
        }
        else {
            return null;
        }
    }

    /**
     * Compares two ItemStacks for equality, testing itemID, metaData, stackSize, and their NBTTagCompounds (if they are
     * present)
     *
     * @param first  The first ItemStack being tested for equality
     * @param second The second ItemStack being tested for equality
     * @return true if the two ItemStacks are equivalent, false otherwise
     */
    public static boolean equals(ItemStack first, ItemStack second) {
        return (Comparators.ID_COMPARATOR.compare(first, second) == 0);
    }

    public static boolean equalsIgnoreStackSize(ItemStack itemStack1, ItemStack itemStack2) {
        return equals(clone(itemStack1, 1), clone(itemStack2, 1));
    }

    public static int compare(ItemStack itemStack1, ItemStack itemStack2) {
        return Comparators.ID_COMPARATOR.compare(itemStack1, itemStack2);
    }

    public static String toString(ItemStack itemStack) {

        if (itemStack != null) {
            if (itemStack.hasTagCompound()) {
                return String.format("%sxitemStack[%s@%s:%s]", itemStack.getCount(), itemStack.getUnlocalizedName(), itemStack.getItemDamage(), itemStack.getTagCompound());
            }
            else {
                return String.format("%sxitemStack[%s@%s]", itemStack.getCount(), itemStack.getUnlocalizedName(), itemStack.getItemDamage());
            }
        }

        return "null";
    }

    public static void setOwner(ItemStack itemStack, EntityPlayer entityPlayer) {

        setOwnerName(itemStack, entityPlayer);
        setOwnerUUID(itemStack, entityPlayer);
    }

    public static String getOwnerName(ItemStack itemStack) {
        return NBTUtils.getString(itemStack, Names.NBT.OWNER);
    }

    public static UUID getOwnerUUID(ItemStack itemStack) {

        if (NBTUtils.getLong(itemStack, Names.NBT.OWNER_UUID_MOST_SIG) != null && NBTUtils.getLong(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG) != null) {
            return new UUID(NBTUtils.getLong(itemStack, Names.NBT.OWNER_UUID_MOST_SIG), NBTUtils.getLong(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG));
        }

        return null;
    }

    public static void setOwnerUUID(ItemStack itemStack, EntityPlayer entityPlayer) {

        NBTUtils.setLong(itemStack, Names.NBT.OWNER_UUID_MOST_SIG, entityPlayer.getUniqueID().getMostSignificantBits());
        NBTUtils.setLong(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG, entityPlayer.getUniqueID().getLeastSignificantBits());
    }

    public static void setOwnerName(ItemStack itemStack, EntityPlayer entityPlayer) {
        NBTUtils.setString(itemStack, Names.NBT.OWNER, entityPlayer.getName());
    }

    public static boolean hasOwner(ItemStack itemStack)
    {
        return (NBTUtils.hasTag(itemStack, Names.NBT.OWNER_UUID_MOST_SIG) && NBTUtils.hasTag(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG)) || NBTUtils.hasTag(itemStack, Names.NBT.OWNER);
    }

    public static boolean hasOwnerUUID(ItemStack itemStack)
    {
        return NBTUtils.hasTag(itemStack, Names.NBT.OWNER_UUID_MOST_SIG) && NBTUtils.hasTag(itemStack, Names.NBT.OWNER_UUID_LEAST_SIG);
    }

    public static boolean hasOwnerName(ItemStack itemStack)
    {
        return NBTUtils.hasTag(itemStack, Names.NBT.OWNER);
    }

    public static ItemStack validateCopy(ItemStack stack){
        if(isValid(stack)){
            return stack.copy();
        }
        else{
            return getEmpty();
        }
    }

    public static ItemStack validateCheck(ItemStack stack){
        if(isValid(stack)){
            return stack;
        }
        else{
            return getEmpty();
        }
    }

    public static boolean isValid(ItemStack stack){
        if(stack == null) LogHelper.fatal("Null ItemStack detected");
        Item i = stack.getItem();
        if(i instanceof IDisableableItem) return !((IDisableableItem) i).isDisabled();
        return !stack.isEmpty();
    }

    public static ItemStack getEmpty(){
        return ItemStack.EMPTY;
    }

    public static int getStackSize(ItemStack stack){
        if(!isValid(stack)){
            return 0;
        }
        else{
            return stack.getCount();
        }
    }

    public static ItemStack setStackSize(ItemStack stack, int size){
        return setStackSize(stack, size, false);
    }

    public static ItemStack setStackSize(ItemStack stack, int size, boolean containerOnEmpty){
        if(size <= 0){
            if(isValid(stack) && containerOnEmpty){
                return stack.getItem().getContainerItem(stack);
            }
            else{
                return getEmpty();
            }
        }
        stack.setCount(size);
        return stack;
    }

    public static ItemStack addStackSize(ItemStack stack, int size){
        return addStackSize(stack, size, false);
    }

    public static ItemStack addStackSize(ItemStack stack, int size, boolean containerOnEmpty){
        return setStackSize(stack, getStackSize(stack)+size, containerOnEmpty);
    }

    public static boolean isIInvEmpty(NonNullList<ItemStack> slots){
        for(ItemStack stack : slots){
            if(ItemStackUtils.isValid(stack)){
                return false;
            }
        }

        return true;
    }

    public static NonNullList<ItemStack> createSlots(int size){
        return NonNullList.withSize(size, getEmpty());
    }

    public static boolean isEmpty(Collection<ItemStack> stacks) {
        if(stacks.isEmpty()) return true;
        else for(ItemStack s : stacks) if (!s.isEmpty()) return false;
        return true;
    }

    public static boolean canAddAll(IItemHandler inv, List<ItemStack> stacks) {

        int slotMax = inv.getSlots();
        int counter = 0;

        for(ItemStack s : stacks) {
            for(int i = 0; i < slotMax; i++) {
                s = inv.insertItem(i, s, true);
                if(s.isEmpty()) break;
            }
            if(s.isEmpty()) counter++;
        }
        return counter == stacks.size();
    }

    public static void addAll(IItemHandler inv, List<ItemStack> stacks) {
        int slotMax = inv.getSlots();
        for(ItemStack s : stacks) {
            for(int i = 0; i < slotMax; i++) {
                s = inv.insertItem(i, s, false);
                if(s.isEmpty()) break;
            }
        }
    }

}
