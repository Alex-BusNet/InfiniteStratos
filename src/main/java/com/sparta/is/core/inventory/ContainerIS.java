package com.sparta.is.core.inventory;

import com.sparta.is.core.utils.helpers.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerIS extends Container
{
    protected final int PLAYER_INVENTORY_ROWS = 3;
    protected final int PLAYER_INVENTORY_COLUMNS = 9;

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }

    protected boolean mergeItemStack(ItemStack itemStack, int slotMin, int slotMax, boolean ascending)
    {
        boolean slotFound = false;
        int currentSlotIndex = ascending ? slotMax - 1 : slotMin;
        Slot slot;
        ItemStack stackInSlot;

        if(itemStack.isStackable())
        {
            while((itemStack.getCount() > 0) && ((!ascending && currentSlotIndex < slotMax) || (ascending && currentSlotIndex >= slotMin)))
            {
                slot = this.inventorySlots.get(currentSlotIndex);
                stackInSlot = slot.getStack();
                if(slot.isItemValid(itemStack) && ItemHelper.equalsIgnoreStackSize(itemStack, stackInSlot))
                {
                    int combinedStackSize = stackInSlot.getCount() + itemStack.getCount();
                    int slotStackSizeLimit = Math.min(stackInSlot.getMaxStackSize(), slot.getSlotStackLimit());
                    if(combinedStackSize <= slotStackSizeLimit)
                    {
                        itemStack.setCount(0);
                        stackInSlot.setCount(combinedStackSize);
                        slot.onSlotChanged();
                        slotFound = true;
                    }
                    else if (stackInSlot.getCount() < slotStackSizeLimit)
                    {
                        itemStack.setCount(itemStack.getCount() - slotStackSizeLimit - stackInSlot.getCount());
                        stackInSlot.setCount(slotStackSizeLimit);
                        slot.onSlotChanged();
                        slotFound = true;
                    }
                }

                currentSlotIndex += ascending ? -1 : 1;
            }
        }

        if(itemStack.getCount() > 0)
        {
            currentSlotIndex = ascending ? slotMax - 1 : slotMin;

            while (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin)
            {
                slot = (Slot) this.inventorySlots.get(currentSlotIndex);
                stackInSlot = slot.getStack();

                if(slot.isItemValid(itemStack) && stackInSlot == null)
                {
                    slot.putStack(ItemHelper.cloneItemStack(itemStack, Math.min(itemStack.getCount(), slot.getSlotStackLimit())));
                    slot.onSlotChanged();

                    if(slot.getStack() != null)
                    {
                        itemStack.setCount(itemStack.getCount() - slot.getStack().getCount());
                        slotFound = true;
                    }

                    break;
                }

                currentSlotIndex += ascending ? -1 : 1;
            }
        }

        return slotFound;
    }
}
