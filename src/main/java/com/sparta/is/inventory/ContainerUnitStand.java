package com.sparta.is.inventory;

import com.sparta.is.item.ItemISUnit;
import com.sparta.is.tileentity.TileEntityUnitStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerUnitStand extends ContainerIS
{
    private TileEntityUnitStand tileEntityUnitStand;


    public ContainerUnitStand(InventoryPlayer inventoryPlayer, TileEntityUnitStand tileEntityUnitStand)
    {
        this.tileEntityUnitStand = tileEntityUnitStand;

        this.addSlotToContainer(new Slot(tileEntityUnitStand, TileEntityUnitStand.IS_UNIT_SLOT_INDEX, 80, 26));

        //Add the player's inventory slots to the container
        for(int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for(int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 84 + inventoryRowIndex *18));
            }
        }

        //Add the player's action bar slots to the container
        for(int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++ actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack())
        {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            /**
             * If shift-clicking outside of ISUnitStation's container,
             * attempt to put stack in first available slot in player's inventory
             */

            if(slotIndex < TileEntityUnitStand.INVENTORY_SIZE)
            {
                if(!this.mergeItemStack(slotItemStack, TileEntityUnitStand.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                /**
                 * If the stack being shift-clicked into ISUnitStation's container
                 * is an IS UNIT, first try to put it in the tool slot.
                 */
                if(slotItemStack.getItem() instanceof ItemISUnit)
                {
                    if(!this.mergeItemStack(slotItemStack, TileEntityUnitStand.IS_UNIT_SLOT_INDEX, TileEntityUnitStand.INVENTORY_SIZE, false))
                    {
                        return null;
                    }
                }
                /**
                 * If the stack is not compatible, don't add it in a slot
                 */
                else
                {
                    return null;
                }
            }

            if(slotItemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }
}
