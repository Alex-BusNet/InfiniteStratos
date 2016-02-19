package com.sparta.is.network.message;

/**
 * BORROWED FROM Equivalent Exchange 3 FOR SAKE OF EARLY TILE ENTITY DEVELOPMENT
 */

import com.sparta.is.tileentity.TileEntityAlchemyArray;
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

public class MessageTileEntityAlchemyArray implements IMessage
{
    public NBTTagCompound tileEntityAlchemyArrayNBT;

    public MessageTileEntityAlchemyArray()
    {
    }

    public MessageTileEntityAlchemyArray(TileEntityAlchemyArray tileEntityAlchemyArray)
    {
        tileEntityAlchemyArrayNBT = new NBTTagCompound();
        tileEntityAlchemyArray.writeToNBT(tileEntityAlchemyArrayNBT);
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
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
                this.tileEntityAlchemyArrayNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void toBytes(ByteBuf byteBuf) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            if (tileEntityAlchemyArrayNBT != null) {
                CompressedStreamTools.writeCompressed(tileEntityAlchemyArrayNBT, byteArrayOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byteBuf.writeInt(byteArrayOutputStream.size());
        if (byteArrayOutputStream.size() > 0) {
            byteBuf.writeBytes(byteArrayOutputStream.toByteArray());
        }
    }

    public static class MessageHandler implements IMessageHandler<MessageTileEntityAlchemyArray, IMessage> {

        @Override
        public IMessage onMessage(MessageTileEntityAlchemyArray message, MessageContext ctx) {

            if (message.tileEntityAlchemyArrayNBT != null) {

                TileEntityAlchemyArray tileEntityAlchemyArray = new TileEntityAlchemyArray();
                tileEntityAlchemyArray.readFromNBT(message.tileEntityAlchemyArrayNBT);

                TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityAlchemyArray.getPos());

                if (tileEntity instanceof TileEntityAlchemyArray) {

                    tileEntity.readFromNBT(message.tileEntityAlchemyArrayNBT);
                    FMLClientHandler.instance().getClient().theWorld.checkLight(tileEntityAlchemyArray.getPos());
                }
            }

            return null;
        }
    }
}