package com.sparta.is.network.message;

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
import java.io.ByteArrayOutputStream;
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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try
        {
            if (tileEntityISStationNBT != null)
            {
                CompressedStreamTools.writeCompressed(tileEntityISStationNBT, outputStream);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        buf.writeInt(outputStream.size());
        if (outputStream.size() > 0)
        {
            buf.writeBytes(outputStream.toByteArray());
        }
    }

    @Override
    public IMessage onMessage(MessageTileEntityISUnitStation message, MessageContext ctx)
    {
        if (message.tileEntityISStationNBT != null)
        {
            TileEntityISStation tileEntityISStation = new TileEntityISStation();
            tileEntityISStation.readFromNBT(message.tileEntityISStationNBT);

            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityISStation.getPos());

            if (tileEntity instanceof TileEntityISStation)
            {
                tileEntity.readFromNBT(message.tileEntityISStationNBT);
                //NAME UPDATE
                FMLClientHandler.instance().getClient().theWorld.checkLight(tileEntity.getPos());
            }
        }

        return null;
    }
}
