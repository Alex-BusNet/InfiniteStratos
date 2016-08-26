package com.sparta.is.network.message;

import com.sparta.is.tileentity.TileEntityIS;
import com.sparta.is.tileentity.TileEntityUnitStand;
import io.netty.buffer.ByteBuf;
import javafx.util.Pair;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import scala.Char;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.HashMap;

public class MessageTileEntityUnitStand implements IMessage, IMessageHandler<MessageTileEntityUnitStand, IMessage>
{
    public NBTTagCompound tileEntityUnitStandNBT;
    private String unitInStand;

    private static final HashMap<Class, Pair<Reader, Writer>> handlers = new HashMap<>();
    private static final HashMap<Class, Field[]> fieldCache = new HashMap<>();

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
    public void toBytes(char[] buf)
    {
        try {
            Class<?> clazz = getClass();
            Field[] clFields = getClassFields(clazz);
            for(Field f : clFields) {
                Class<?> type = f.getType();
                if(acceptField(f, type))
                    writeField(f, type, buf);
            }
        } catch(Exception e) {
            throw new RuntimeException("Error at writing packet " + this, e);
        }

    }

    private static Field[] getClassFields(Class<?> clazz)
    {
        if(fieldCache.containsValue(clazz))
            return fieldCache.get(clazz);
        else {
            Field[] fields = clazz.getFields();
            Arrays.sort(fields, (Field f1, Field f2) -> {
                return f1.getName().compareTo(f2.getName());
            });
            fieldCache.put(clazz, fields);
            return fields;
        }
    }

    private final void writeField(Field f, Class clazz, char[] buf) throws IllegalArgumentException, IllegalAccessException, IOException
    {
        Pair<Reader, Writer> handler = getHandler(clazz);
        handler.getValue().write(buf);

    }

    private final void readField(Field f, Class clazz, CharBuffer buf) throws IllegalArgumentException, IllegalAccessException, IOException
    {
        Pair<Reader, Writer> handler = getHandler(clazz);
        f.set(this, handler.getKey().read(buf));
    }

    private static Pair<Reader, Writer> getHandler(Class<?> clazz) {
        Pair<Reader, Writer> pair = handlers.get(clazz);
        if(pair == null)
            throw new RuntimeException("No R/W handler for  " + clazz);
        return pair;
    }

    private static boolean acceptField(Field f, Class<?> type) {
        int mods = f.getModifiers();
        if(Modifier.isFinal(mods) || Modifier.isStatic(mods) || Modifier.isTransient(mods))
            return false;

        return  handlers.containsKey(type);
    }

    @Override
    public IMessage onMessage(MessageTileEntityUnitStand message, MessageContext ctx)
    {
        if (message.tileEntityUnitStandNBT != null)
        {
            TileEntityIS tileEntityIS = new TileEntityIS();
            tileEntityIS.readFromNBT(message.tileEntityUnitStandNBT);

            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityIS.getPos());

            if (tileEntity instanceof TileEntityUnitStand)
            {
                tileEntity.readFromNBT(message.tileEntityUnitStandNBT);
                //NAME UPDATE
//                FMLClientHandler.instance().getClient().theWorld.func_147451_t(tileEntity.getPos());
            }
        }

        return null;
    }
}
