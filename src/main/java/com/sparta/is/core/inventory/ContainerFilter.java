package com.sparta.is.core.inventory;


import com.sparta.is.core.inventory.slot.SlotFilter;
import com.sparta.is.core.inventory.slot.SlotImmovable;
import com.sparta.is.core.utils.ItemStackUtils;
import com.sparta.is.core.utils.helpers.ItemStackHandlerCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFilter extends Container
{
    public static final int SLOT_AMOUNT = 24;

    private final ItemStackHandlerCustom filterInventory = new ItemStackHandlerCustom(SLOT_AMOUNT);
    private final InventoryPlayer inventory;

    public ContainerFilter(InventoryPlayer inventory){
        this.inventory = inventory;

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 6; j++){
                this.addSlotToContainer(new SlotFilter(this.filterInventory, j+(i*6), 35+j*18, 10+i*18));
            }
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 9; j++){
                this.addSlotToContainer(new Slot(inventory, j+i*9+9, 8+j*18, 94+i*18));
            }
        }
        for(int i = 0; i < 9; i++){
            if(i == inventory.currentItem){
                this.addSlotToContainer(new SlotImmovable(inventory, i, 8+i*18, 152));
            }
            else{
                this.addSlotToContainer(new Slot(inventory, i, 8+i*18, 152));
            }
        }

//        ItemStack stack = inventory.getCurrentItem();
//        if( SlotFilter.isFilter(stack)){
//            ItemDrill.loadSlotsFromNBT(this.filterInventory, inventory.getCurrentItem());
//        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot){
        int inventoryStart = SLOT_AMOUNT;
        int inventoryEnd = inventoryStart+26;
        int hotbarStart = inventoryEnd+1;
        int hotbarEnd = hotbarStart+8;

        Slot theSlot = this.inventorySlots.get(slot);

        if(theSlot != null && theSlot.getHasStack()){
            ItemStack newStack = theSlot.getStack();
            ItemStack currentStack = newStack.copy();

            //Other Slots in Inventory excluded
            if(slot >= inventoryStart){
                //Shift from Inventory
                //
                if(slot >= inventoryStart && slot <= inventoryEnd){
                    if(!this.mergeItemStack(newStack, hotbarStart, hotbarEnd+1, false)){
                        return ItemStackUtils.getEmpty();
                    }
                }
                else if(slot >= inventoryEnd+1 && slot < hotbarEnd+1 && !this.mergeItemStack(newStack, inventoryStart, inventoryEnd+1, false)){
                    return ItemStackUtils.getEmpty();
                }
            }
            else if(!this.mergeItemStack(newStack, inventoryStart, hotbarEnd+1, false)){
                return ItemStackUtils.getEmpty();
            }

            if(!ItemStackUtils.isValid(newStack)){
                theSlot.putStack(ItemStackUtils.getEmpty());
            }
            else{
                theSlot.onSlotChanged();
            }

            if(ItemStackUtils.getStackSize(newStack) == ItemStackUtils.getStackSize(currentStack)){
                return ItemStackUtils.getEmpty();
            }
            theSlot.onTake(player, newStack);

            return currentStack;
        }
        return ItemStackUtils.getEmpty();
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player){
        if(SlotFilter.checkFilter(this, slotId, player)){
            return ItemStackUtils.getEmpty();
        }
        else if(clickTypeIn == ClickType.SWAP && dragType == this.inventory.currentItem){
            return ItemStack.EMPTY;
        }
        else{
            return super.slotClick(slotId, dragType, clickTypeIn, player);
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player){
        ItemStack stack = this.inventory.getCurrentItem();
//        if(SlotFilter.isFilter(stack)){
//            ItemDrill.writeSlotsToNBT(this.filterInventory, this.inventory.getCurrentItem());
//        }
        super.onContainerClosed(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player){
        return true;
    }
}
