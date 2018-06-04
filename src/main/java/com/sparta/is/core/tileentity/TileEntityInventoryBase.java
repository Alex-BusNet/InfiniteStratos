package com.sparta.is.core.tileentity;

import com.sparta.is.core.utils.ItemStackUtils;
import com.sparta.is.core.utils.helpers.ItemStackHandlerCustom;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileEntityInventoryBase extends TileEntityIS
{
    public final ItemStackHandlerCustom slots;

    public TileEntityInventoryBase(int slots, String name){
        super(name);

        this.slots = new ItemStackHandlerCustom(slots){
            @Override
            public boolean canInsert(ItemStack stack, int slot){
                return TileEntityInventoryBase.this.isItemValidForSlot(slot, stack);
            }

            @Override
            public boolean canExtract(ItemStack stack, int slot){
                return TileEntityInventoryBase.this.canExtractItem(slot, stack);
            }

            @Override
            public int getSlotLimit(int slot){
                return TileEntityInventoryBase.this.getMaxStackSizePerSlot(slot);
            }

            @Override
            protected void onContentsChanged(int slot){
                super.onContentsChanged(slot);
                TileEntityInventoryBase.this.markDirty();
            }
        };
    }

    public static void saveSlots(IItemHandler slots, NBTTagCompound compound){
        if(slots != null && slots.getSlots() > 0){
            NBTTagList tagList = new NBTTagList();
            for(int i = 0; i < slots.getSlots(); i++){
                ItemStack slot = slots.getStackInSlot(i);
                NBTTagCompound tagCompound = new NBTTagCompound();
                if( ItemStackUtils.isValid(slot)){
                    slot.writeToNBT(tagCompound);
                }
                tagList.appendTag(tagCompound);
            }
            compound.setTag("Items", tagList);
        }
    }

    public static void loadSlots(IItemHandlerModifiable slots, NBTTagCompound compound){
        if(slots != null && slots.getSlots() > 0){
            NBTTagList tagList = compound.getTagList("Items", 10);
            for(int i = 0; i < slots.getSlots(); i++){
                NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                slots.setStackInSlot(i, tagCompound != null && tagCompound.hasKey("id") ? new ItemStack(tagCompound) : ItemStackUtils.getEmpty());
            }
        }
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, NBTType type){
        super.writeSyncableNBT(compound, type);
        if(type == NBTType.SAVE_TILE || (type == NBTType.SYNC && this.shouldSyncSlots())){
            saveSlots(this.slots, compound);
        }
    }

    @Override
    public IItemHandler getItemHandler(EnumFacing facing){
        return this.slots;
    }

    public boolean isItemValidForSlot(int slot, ItemStack stack){
        return true;
    }

    public boolean canExtractItem(int slot, ItemStack stack){
        return true;
    }

    public int getMaxStackSizePerSlot(int slot){
        return 64;
    }

    public boolean shouldSyncSlots(){
        return false;
    }

    @Override
    public void markDirty(){
        super.markDirty();

        if(this.shouldSyncSlots()){
            this.sendUpdate();
        }
    }

    @Override
    public int getComparatorStrength(){
        return ItemHandlerHelper.calcRedstoneFromInventory(this.slots);
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, NBTType type){
        super.readSyncableNBT(compound, type);
        if(type == NBTType.SAVE_TILE || (type == NBTType.SYNC && this.shouldSyncSlots())){
            loadSlots(this.slots, compound);
        }
    }
}
