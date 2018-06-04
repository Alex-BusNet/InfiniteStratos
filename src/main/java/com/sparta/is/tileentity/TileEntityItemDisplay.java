package com.sparta.is.tileentity;

import com.sparta.is.core.tileentity.CustomEnergyStorage;
import com.sparta.is.core.tileentity.TileEntityInventoryBase;
import com.sparta.is.core.utils.ItemStackUtils;
import com.sparta.is.core.utils.interfaces.IDisplayStandItem;
import com.sparta.is.core.utils.interfaces.IEnergyDisplay;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityItemDisplay extends TileEntityInventoryBase implements IEnergyDisplay
{
    public final CustomEnergyStorage storage = new CustomEnergyStorage(80000, 1000, 0);
    private int oldEnergy;

    public TileEntityItemDisplay()
    {
        super(1, "itemDisplay");
    }

    @Override
    public void updateEntity(){
        super.updateEntity();

        if(!this.world.isRemote)
        {
            if( ItemStackUtils.isValid(this.slots.getStackInSlot(0)) && !this.isRedstonePowered)
            {
                IDisplayStandItem item = this.convertToDisplayStandItem(this.slots.getStackInSlot(0).getItem());
                if(item != null)
                {
                    int energy = item.getUsePerTick(this.slots.getStackInSlot(0), this, this.ticksElapsed);
                    if(this.storage.getEnergyStored() >= energy)
                    {
                        if(item.update(this.slots.getStackInSlot(0), this, this.ticksElapsed))
                        {
                            this.storage.extractEnergyInternal(energy, false);
                        }
                    }
                }
            }

            if(this.oldEnergy != this.storage.getEnergyStored() && this.sendUpdateWithInterval())
            {
                this.oldEnergy = this.storage.getEnergyStored();
            }
        }
    }

    @Override
    public boolean shouldSyncSlots(){
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack){
        return true;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);

        NBTTagList tagList = new NBTTagList();

        for(ItemStack itemStack : this.slots.getItems())
        {
            NBTTagCompound tagCompound = new NBTTagCompound();

            if(!itemStack.isEmpty())
            {
                itemStack.writeToNBT(tagCompound);
            }

            tagList.appendTag(tagCompound);
        }

        compound.setTag("DisplayItem", tagList);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        if(compound.hasKey("DisplayItem", 9))
        {
            NBTTagList tagList = compound.getTagList("DisplayItem", 10);
            this.slots.insertItem(0, new ItemStack(tagList.getCompoundTagAt(0)), false);
        }
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, NBTType type)
    {
        super.writeSyncableNBT(compound, type);
        this.storage.writeToNBT(compound);
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, NBTType type)
    {
        super.readSyncableNBT(compound, type);
        this.storage.readFromNBT(compound);
    }

    private IDisplayStandItem convertToDisplayStandItem(Item item)
    {
        if(item instanceof IDisplayStandItem)
        {
            return (IDisplayStandItem)item;
        }
        else if(item instanceof ItemBlock )
        {
            Block block = Block.getBlockFromItem(item);
            if(block instanceof IDisplayStandItem)
            {
                return (IDisplayStandItem)block;
            }
        }
        return null;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack){
        return true;
    }

    @Override
    public CustomEnergyStorage getEnergyStorage(){
        return this.storage;
    }

    @Override
    public boolean needsHoldShift(){
        return false;
    }

    @Override
    public int getMaxStackSizePerSlot(int slot){
        return 1;
    }

    @Override
    public IEnergyStorage getEnergyStorage(EnumFacing facing){
        return this.storage;
    }

    @Override
    public boolean hasFastRenderer()
    {
        return true;
    }
}
