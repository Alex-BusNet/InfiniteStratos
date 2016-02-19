package com.sparta.is.network.message;

import com.sparta.is.tileentity.TileEntityIS;
import com.sparta.is.tileentity.TileEntityUnitStand;
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

public class MessageTileEntityUnitStand implements IMessage, IMessageHandler<MessageTileEntityUnitStand, IMessage>
{
    public NBTTagCompound tileEntityUnitStandNBT;
    private String unitInStand;

    public MessageTileEntityUnitStand()
    {

    }

    public MessageTileEntityUnitStand(TileEntityUnitStand tileEntityUnitStand)
    {
        tileEntityUnitStandNBT = new NBTTagCompound();
        tileEntityUnitStand.writeToNBT(tileEntityUnitStandNBT);
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
                this.tileEntityUnitStandNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
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
            if (tileEntityUnitStandNBT != null)
            {
                compressedNBT = CompressedStreamTools.compress(tileEntityUnitStandNBT);
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
    public IMessage onMessage(MessageTileEntityUnitStand message, MessageContext ctx)
    {
        if (message.tileEntityUnitStandNBT != null)
        {
            TileEntityIS tileEntityIS = new TileEntityIS();
            tileEntityIS.readFromNBT(message.tileEntityUnitStandNBT);

            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityIS.xCoord, tileEntityIS.yCoord, tileEntityIS.zCoord);

            if (tileEntity instanceof TileEntityUnitStand)
            {
                tileEntity.readFromNBT(message.tileEntityUnitStandNBT);
                //NAME UPDATE
                FMLClientHandler.instance().getClient().theWorld.func_147451_t(tileEntityIS.xCoord, tileEntityIS.yCoord, tileEntityIS.zCoord);
            }
        }

        return null;
    }
}
