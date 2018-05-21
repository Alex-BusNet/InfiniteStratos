package com.sparta.is.utils;

import com.sparta.is.api.energy.IEnergyConnection;
import com.sparta.is.api.energy.IEnergyProvider;
import com.sparta.is.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class EnergyHelper
{
    public static final int RF_PER_MJ = 10; // Official Ratio of RF to MJ
    // (BuildCraft)
    public static final int RF_PER_EU = 4; // Official Ratio of RF to EU
    // (IndustrialCraft)

    private EnergyHelper() {

    }

    /* NBT TAG HELPER */
    public static void addEnergyInformation(ItemStack stack, List<String> list) {

        if (stack.getItem() instanceof IEnergy ) {
            list.add(StringHelper.localize("info.sparta.charge") + ": " + StringHelper.getScaledNumber(stack.getTagCompound().getInteger("Energy")) + " / "
                    + StringHelper.getScaledNumber(((IEnergy) stack.getItem()).getMaxEnergyStored(stack)) + " RF");
        }
    }

    /* IEnergyContainer Interaction */
    public static int extractEnergyFromContainer(ItemStack container, int maxExtract, boolean simulate) {

        return isEnergyContainerItem(container) ? ((IEnergy) container.getItem()).extractEnergy(container, maxExtract, simulate) : 0;
    }

    public static int insertEnergyIntoContainer(ItemStack container, int maxReceive, boolean simulate) {

        return isEnergyContainerItem(container) ? ((IEnergy) container.getItem()).receiveEnergy(container, maxReceive, simulate) : 0;
    }

    public static int extractEnergyFromHeldContainer(EntityPlayer player, int maxExtract, boolean simulate) {

        ItemStack container = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

        return isEnergyContainerItem(container) ? ((IEnergy) container.getItem()).extractEnergy(container, maxExtract, simulate) : 0;
    }

    public static int insertEnergyIntoHeldContainer(EntityPlayer player, int maxReceive, boolean simulate) {

        ItemStack container = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

        return isEnergyContainerItem(container) ? ((IEnergy) container.getItem()).receiveEnergy(container, maxReceive, simulate) : 0;
    }

    public static boolean isPlayerHoldingEnergyContainerItem(EntityPlayer player) {

        return isEnergyContainerItem(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST));
    }

    public static boolean isEnergyContainerItem(ItemStack container) {

        return container != null && container.getItem() instanceof IEnergy;
    }

    public static ItemStack setDefaultEnergyTag(ItemStack container, int energy) {

        if (!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }
        container.getTagCompound().setInteger("Energy", energy);

        return container;
    }

    /* IEnergy Interaction */
    public static int extractEnergyFromAdjacentEnergyProvider(TileEntity tile, int side, int energy, boolean simulate) {

        TileEntity handler = BlockHelper.getAdjacentTileEntity(tile, side);

        return handler instanceof IEnergyProvider ? ((IEnergyProvider) handler).extractEnergy(EnumFacing.VALUES[side ^ 1], energy, simulate) : 0;
    }

    public static int insertEnergyIntoAdjacentEnergyReceiver(TileEntity tile, int side, int energy, boolean simulate) {

        TileEntity handler = BlockHelper.getAdjacentTileEntity(tile, side);

        return handler instanceof IEnergyReceiver ? ((IEnergyReceiver) handler).receiveEnergy(EnumFacing.VALUES[side ^ 1], energy, simulate) : 0;
    }

    public static boolean isAdjacentEnergyConnectableFromSide(TileEntity tile, int side) {

        TileEntity handler = BlockHelper.getAdjacentTileEntity(tile, side);

        return isEnergyConnectableFromSide(handler, EnumFacing.VALUES[side ^ 1]);
    }

    public static boolean isEnergyConnectableFromSide(TileEntity tile, EnumFacing from) {

        return tile instanceof IEnergyConnection ? ((IEnergyConnection) tile).canConnectEnergy(from) : false;
    }

    public static boolean isAdjacentEnergyReceiverFromSide(TileEntity tile, int side) {

        TileEntity handler = BlockHelper.getAdjacentTileEntity(tile, side);

        return isEnergyReceiverFromSide(handler, EnumFacing.VALUES[side ^ 1]);
    }

    public static boolean isEnergyReceiverFromSide(TileEntity tile, EnumFacing from) {

        return tile instanceof IEnergyReceiver ? ((IEnergyReceiver) tile).canConnectEnergy(from) : false;
    }

    public static boolean isAdjacentEnergyProviderFromSide(TileEntity tile, int side) {

        TileEntity handler = BlockHelper.getAdjacentTileEntity(tile, side);

        return isEnergyProviderFromSide(handler, EnumFacing.VALUES[side ^ 1]);
    }

    public static boolean isEnergyProviderFromSide(TileEntity tile, EnumFacing from) {

        return tile instanceof IEnergyProvider ? ((IEnergyProvider) tile).canConnectEnergy(from) : false;
    }
}
