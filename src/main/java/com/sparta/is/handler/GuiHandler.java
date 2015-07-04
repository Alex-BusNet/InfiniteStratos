package com.sparta.is.handler;

import com.sparta.is.client.gui.inventory.GuiISUnitStation;
import com.sparta.is.client.gui.inventory.GuiUnitStand;
import com.sparta.is.inventory.ContainerISUnitStation;
import com.sparta.is.inventory.ContainerUnitStand;
import com.sparta.is.reference.GUIs;
import com.sparta.is.tileentity.TileEntityISStation;
import com.sparta.is.tileentity.TileEntityUnitStand;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if(id == GUIs.IS_UNIT_STATION.ordinal())
        {
            TileEntityISStation tileEntityISStation = (TileEntityISStation) world.getTileEntity(x, y, z);
            return new ContainerISUnitStation(entityPlayer.inventory, tileEntityISStation);
        }
        else if(id == GUIs.UNIT_STAND.ordinal())
        {
            TileEntityUnitStand tileEntityUnitStand = (TileEntityUnitStand) world.getTileEntity(x, y, z);
            return new ContainerUnitStand(entityPlayer.inventory, tileEntityUnitStand);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if(id == GUIs.IS_UNIT_STATION.ordinal())
        {
            TileEntityISStation tileEntityISStation = (TileEntityISStation) world.getTileEntity(x, y, z);
            return new GuiISUnitStation(entityPlayer.inventory, tileEntityISStation);
        }
        else if(id == GUIs.UNIT_STAND.ordinal())
        {
            TileEntityUnitStand tileEntityUnitStand = (TileEntityUnitStand) world.getTileEntity(x, y, z);
            return new GuiUnitStand(entityPlayer.inventory, tileEntityUnitStand);
        }

        return null;
    }
}
