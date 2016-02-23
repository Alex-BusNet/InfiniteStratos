package com.sparta.is.waila;

import com.sparta.is.reference.Names;
import com.sparta.is.tileentity.TileEntityISStation;
import com.sparta.is.tileentity.TileEntityUnitStand;
import mcp.mobius.waila.api.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class WailaDataProvider implements IWailaDataProvider
{

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        if(accessor.getTileEntity() instanceof TileEntityISStation)
        {
            currentTip.set(0, String.format("%s%s", SpecialChars.WHITE, StatCollector.translateToLocal(Names.Blocks.IS_UNIT_STATION)));
        }
        else if(accessor.getTileEntity() instanceof TileEntityUnitStand)
        {
            currentTip.set(0, String.format("%s%s", SpecialChars.WHITE, StatCollector.translateToLocal(Names.Blocks.UNIT_STAND)));
        }
        return currentTip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currentTip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currentTip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
    {
        return null;
    }

    public static  void callbackRegister(IWailaRegistrar registrar)
    {
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityISStation.class);
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityUnitStand.class);
    }

}
