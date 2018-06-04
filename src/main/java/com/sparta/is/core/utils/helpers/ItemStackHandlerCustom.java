package com.sparta.is.core.utils.helpers;

import com.sparta.is.core.utils.ItemStackUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerCustom extends ItemStackHandler
{
    private boolean tempIgnoreConditions;

    public ItemStackHandlerCustom(int slots){
        super(slots);
    }

    public void decrStackSize(int slot, int amount){
        this.setStackInSlot(slot, ItemStackUtils.addStackSize(this.getStackInSlot(slot), -amount));
    }

    public NonNullList<ItemStack> getItems(){
        return this.stacks;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate){
        if(!ItemStackUtils.isValid(stack)){
            return ItemStackUtils.getEmpty();
        }
        this.validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = this.getStackLimit(slot, stack);
        if(ItemStackUtils.isValid(existing)){
            if(!ItemHandlerHelper.canItemStacksStack(stack, existing)){
                return stack;
            }
            limit -= existing.getCount();
        }
        if(limit <= 0){
            return stack;
        }

        if(!this.tempIgnoreConditions && !this.canInsert(stack, slot)){
            return stack;
        }

        boolean reachedLimit = stack.getCount() > limit;
        if(!simulate){
            if(!ItemStackUtils.isValid(existing)){
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else{
                existing.grow(reachedLimit ? limit : stack.getCount());
            }

            this.onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()-limit) : ItemStack.EMPTY;

    }

    public ItemStack insertItemInternal(int slot, ItemStack stack, boolean simulate){
        this.tempIgnoreConditions = true;
        ItemStack result = this.insertItem(slot, stack, simulate);
        this.tempIgnoreConditions = false;
        return result;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate){
        if(amount <= 0){
            return ItemStackUtils.getEmpty();
        }
        this.validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);
        if(!ItemStackUtils.isValid(existing)){
            return ItemStackUtils.getEmpty();
        }

        int toExtract = Math.min(amount, existing.getMaxStackSize());
        if(toExtract <= 0){
            return ItemStackUtils.getEmpty();
        }

        if(!this.tempIgnoreConditions && !this.canExtract(this.getStackInSlot(slot), slot)){
            return ItemStackUtils.getEmpty();
        }

        if(existing.getCount() <= toExtract){
            if(!simulate){
                this.stacks.set(slot, ItemStackUtils.getEmpty());
                this.onContentsChanged(slot);
                return existing;
            }
            return existing.copy();
        }
        else{
            if(!simulate){
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount()-toExtract));
                this.onContentsChanged(slot);
            }
            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    public ItemStack extractItemInternal(int slot, int amount, boolean simulate){
        this.tempIgnoreConditions = true;
        ItemStack result = this.extractItem(slot, amount, simulate);
        this.tempIgnoreConditions = false;
        return result;
    }

    public boolean canInsert(ItemStack stack, int slot){
        return true;
    }

    public boolean canExtract(ItemStack stack, int slot){
        return true;
    }
}
