package com.sparta.is.inventory;


import com.sparta.is.item.ItemISEqualizer;
import com.sparta.is.tileentity.TileEntityISStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerISUnitStation extends ContainerIS
{
    private TileEntityISStation tileEntityISStation;

    public ContainerISUnitStation(InventoryPlayer inventoryPlayer, TileEntityISStation tileEntityISStation)
    {
        this.tileEntityISStation = tileEntityISStation;
        //                               inventory            slotIndex                                  xPos yPos
        this.addSlotToContainer(new Slot(tileEntityISStation, TileEntityISStation.EQUALIZER_SLOT_1_INDEX, 14, 27));
        this.addSlotToContainer(new Slot(tileEntityISStation, TileEntityISStation.EQUALIZER_SLOT_2_INDEX, 145, 27));
        this.addSlotToContainer(new Slot(tileEntityISStation, TileEntityISStation.EQUALIZER_SLOT_3_INDEX, 14, 64));
        this.addSlotToContainer(new Slot(tileEntityISStation, TileEntityISStation.EQUALIZER_SLOT_4_INDEX, 145, 64));

        //Add the player's inventory slots to the container
        for(int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for(int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 6 + inventoryColumnIndex * 18, 99 + inventoryRowIndex *18));
            }
        }

        //Add the player's action bar slots to the container
        for(int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++ actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 6 + actionBarSlotIndex * 18, 157));
        }

        //Find what unit is in the stand and render its name in the maintenance station GUI

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

            if(slotIndex < TileEntityISStation.INVENTORY_SIZE)
            {
                if(!this.mergeItemStack(slotItemStack, TileEntityISStation.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                /**
                 * If the item being clicked is a valid type for the Equalizer slot,
                 * try to put it in the first available Equalizer slot if available
                 */
                if(slotItemStack.getItem() instanceof ItemISEqualizer)
                {
                    if(!this.mergeItemStack(slotItemStack, TileEntityISStation.EQUALIZER_SLOT_1_INDEX, TileEntityISStation.EQUALIZER_SLOT_4_INDEX, false))
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
