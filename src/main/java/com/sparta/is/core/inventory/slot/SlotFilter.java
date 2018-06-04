package com.sparta.is.core.inventory.slot;

import com.sparta.is.core.item.ItemFilter;
import com.sparta.is.core.utils.ItemStackUtils;
import com.sparta.is.core.utils.compat.FilterSettings;
import com.sparta.is.core.utils.helpers.ItemStackHandlerCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFilter extends SlotItemHandlerUnconditioned
{
    public SlotFilter(ItemStackHandlerCustom inv, int slot, int x, int y){
        super(inv, slot, x, y);
    }

    public SlotFilter(FilterSettings inv, int slot, int x, int y){
        this(inv.filterInventory, slot, x, y);
    }

    public static boolean checkFilter(Container container, int slotId, EntityPlayer player){
        if(slotId >= 0 && slotId < container.inventorySlots.size()){
            Slot slot = container.getSlot(slotId);
            if(slot instanceof SlotFilter){
                ((SlotFilter)slot).slotClick(player);
                return true;
            }
        }
        return false;
    }

    public static boolean isFilter(ItemStack stack){
        return ItemStackUtils.isValid(stack) && stack.getItem() instanceof ItemFilter;
    }

    private void slotClick(EntityPlayer player){
        ItemStack heldStack = player.inventory.getItemStack();
        ItemStack stackInSlot = this.getStack();

        if(ItemStackUtils.isValid(stackInSlot) && !ItemStackUtils.isValid(heldStack)){
            if(isFilter(stackInSlot)){
                player.inventory.setItemStack(stackInSlot);
            }

            this.putStack(ItemStackUtils.getEmpty());
        }
        else if(ItemStackUtils.isValid(heldStack)){
            if(!isFilter(stackInSlot)){
                this.putStack(ItemStackUtils.setStackSize(heldStack.copy(), 1));

                if(isFilter(heldStack)){
                    player.inventory.setItemStack(ItemStackUtils.addStackSize(heldStack, -1));
                }
            }
        }
    }

    @Override
    public boolean isItemValid(ItemStack stack){
        return false;
    }

    @Override
    public void putStack(ItemStack stack){
        super.putStack(ItemStackUtils.validateCopy(stack));
    }

    @Override
    public boolean canTakeStack(EntityPlayer player){
        return false;
    }
}
