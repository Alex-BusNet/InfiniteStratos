package com.sparta.is.tileentity;

import com.sparta.is.armor.ArmorIS;
import com.sparta.is.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;

public class TileEntityISUnitStation extends TileEntityIS implements IInventory
{
    public static final int INVENTORY_SIZE = 4;
    public static final int EQUALIZER_SLOT_1_INDEX = 0;
    public static final int EQUALIZER_SLOT_2_INDEX = 1;
    public static final int EQUALIZER_SLOT_3_INDEX = 2;
    public static final int EQUALIZER_SLOT_4_INDEX = 3;

    private ItemStack[] inventory;

    private ArmorIS storedUnit = null;

    public TileEntityISUnitStation()
    {
        super(Names.TileEntities.IS_UNIT_STATION);
        inventory = new ItemStack[INVENTORY_SIZE];
        storedUnit = null;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public boolean isEmpty()
    {
        return storedUnit != null;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.getCount() <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.getCount() == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.getCount() > getInventoryStackLimit())
        {
            itemStack.setCount(getInventoryStackLimit());
        }
    }

//    @Override
//    public String getInventoryName()
//    {
//        return this.hasCustomInventoryName() ? this.getCustomName() : Names.Containers.IS_UNIT_MAINTENANCE_STATION;
//    }
//
//    @Override
//    public boolean hasCustomInventoryName()
//    {
//        return this.hasCustomName();
//    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {

    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return false;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {

    }

    public void setStoredUnit(ArmorIS unit)
    {
        this.storedUnit = unit;
    }

    public String getUnitName()
    {
        if(storedUnit != null)
            return this.storedUnit.getUnlocalizedName();
        else { return ""; }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }


        }
        nbtTagCompound.setTag("Items", tagList);

        return nbtTagCompound;
    }

//    @Override
//    public void readFromNBT(NBTTagCompound nbtTagCompound)
//    {
//        super.readFromNBT(nbtTagCompound);
//
//        // Read in the ItemStacks in the inventory from NBT
//        NBTTagList tagList = nbtTagCompound.getTagList("Items", 10);
//        inventory = new ItemStack[this.getSizeInventory()];
//        for (int i = 0; i < tagList.tagCount(); ++i)
//        {
//            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
//            byte slotIndex = tagCompound.getByte("Slot");
//            if (slotIndex >= 0 && slotIndex < inventory.length)
//            {
//                inventory[slotIndex] =
//            }
//        }
//    }

    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return null;
    }
}
