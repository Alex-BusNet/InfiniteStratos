package com.sparta.is.network.message;

import com.sparta.is.entity.EntityISTabane;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MessageEntityTabane implements IMessage, IMessageHandler<MessageEntityTabane, IMessage>
{

    public NBTTagCompound entityTabaneNBT;

    public MessageEntityTabane()
    {

    }

    public MessageEntityTabane(EntityISTabane entityISTabane)
    {
        entityTabaneNBT = new NBTTagCompound();
        entityISTabane.writeToNBT(entityTabaneNBT);
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
                this.entityTabaneNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
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
            if (entityTabaneNBT != null)
            {
                compressedNBT = CompressedStreamTools.writeCompressed(entityTabaneNBT);
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
    public IMessage onMessage(MessageEntityTabane message, MessageContext ctx)
    {
        if (message.entityTabaneNBT != null)
        {
            EntityISTabane entityISTabane = new EntityISTabane(FMLClientHandler.instance().getWorldClient());
            entityISTabane.readFromNBT(message.entityTabaneNBT);

            Entity entityIS = FMLClientHandler.instance().getClient().theWorld.getEntityByID(entityISTabane.getEntityId());

            if (entityIS instanceof EntityISTabane)
            {
                entityIS.readFromNBT(message.entityTabaneNBT);
                //NAME UPDATE
                FMLClientHandler.instance().getClient().theWorld.func_147451_t(entityISTabane.chunkCoordX, entityISTabane.chunkCoordY, entityISTabane.chunkCoordZ);
            }
        }

        return null;
    }

}
