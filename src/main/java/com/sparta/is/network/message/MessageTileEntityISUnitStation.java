package com.sparta.is.network.message;

import com.sparta.is.tileentity.TileEntityAlchemyArray;
import com.sparta.is.tileentity.TileEntityISStation;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MessageTileEntityISUnitStation implements IMessage, IMessageHandler<MessageTileEntityISUnitStation, IMessage>
{

    public NBTTagCompound tileEntityISStationNBT;

    public MessageTileEntityISUnitStation()
    {

    }

    public MessageTileEntityISUnitStation(TileEntityISStation tileEntityISStation)
    {
        tileEntityISStationNBT = new NBTTagCompound();
        tileEntityISStation.writeToNBT(tileEntityISStationNBT);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        byte[] compressedNBT = null;
        int readableBytes = buf.readInt();

        if (readableBytes > 0)
        {
            compressedNBT = buf.readBytes(readableBytes).array();
        }

        if (compressedNBT != null)
        {
            try
            {
                this.tileEntityISStationNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        byte[] compressedNBT = null;

        try
        {
            if (tileEntityISStationNBT != null)
            {
                compressedNBT = CompressedStreamTools.compress(tileEntityISStationNBT);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (compressedNBT != null)
        {
            buf.writeInt(compressedNBT.length);
            buf.writeBytes(compressedNBT);
        }
        else
        {
            buf.writeInt(0);
        }
    }

    @Override
    public IMessage onMessage(MessageTileEntityISUnitStation message, MessageContext ctx)
    {
        if (message.tileEntityISStationNBT != null)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = new TileEntityAlchemyArray();
            tileEntityAlchemyArray.readFromNBT(message.tileEntityISStationNBT);

            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityAlchemyArray.xCoord, tileEntityAlchemyArray.yCoord, tileEntityAlchemyArray.zCoord);

            if (tileEntity instanceof TileEntityISStation)
            {
                tileEntity.readFromNBT(message.tileEntityISStationNBT);
                //NAME UPDATE
                FMLClientHandler.instance().getClient().theWorld.func_147451_t(tileEntityAlchemyArray.xCoord, tileEntityAlchemyArray.yCoord, tileEntityAlchemyArray.zCoord);
            }
        }

        return null;
    }
}
