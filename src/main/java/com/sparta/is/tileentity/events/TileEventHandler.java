package com.sparta.is.tileentity.events;

import com.sparta.is.tileentity.TileEntityIS;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TileEventHandler
{
    private final Method method;

    public TileEventHandler(Method method)
    {
        this.method = method;
    }

    //Tick
    public void tick(TileEntityIS tileEntityIS)
    {
        try
        {
            this.method.invoke(tileEntityIS);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalStateException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new IllegalStateException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new IllegalStateException(e);
        }
    }

    //WORLD_NBT
    public void writeToNBT(TileEntityIS tileEntityIS, NBTTagCompound data)
    {
        try
        {
            this.method.invoke(tileEntityIS, data);
        }
        catch(IllegalAccessException e)
        {
            throw new IllegalStateException(e);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalStateException(e);
        }
        catch(InvocationTargetException e)
        {
            throw new IllegalStateException(e);
        }
    }

    //WORLD_NBT
    public void readFromNBT(TileEntityIS tileEntityIS, NBTTagCompound data)
    {
        try
        {
            this.method.invoke(tileEntityIS, data);
        }
        catch(IllegalAccessException e)
        {
            throw new IllegalStateException(e);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalStateException(e);
        }
        catch(InvocationTargetException e)
        {
            throw new IllegalStateException(e);
        }
    }

    // NETWORK
    public void writeToStream( TileEntityIS tile, ByteBuf data )
    {
        try
        {
            this.method.invoke(tile, data);
        }
        catch( IllegalAccessException e)
        {
            throw new IllegalStateException( e );
        }
        catch( IllegalArgumentException e )
        {
            throw new IllegalStateException( e );
        }
        catch( InvocationTargetException e )
        {
            throw new IllegalStateException( e );
        }
    }

    // NETWORK

    /**
     * returning true from this method, will update the block's render
     *
     * @param data data of stream
     *
     * @return true of method could be invoked
     */
    @SideOnly( Side.CLIENT )
    public boolean readFromStream( TileEntityIS tile, ByteBuf data )
    {
        try
        {
            return (Boolean) this.method.invoke( tile, data );
        }
        catch( IllegalAccessException e )
        {
            throw new IllegalStateException( e );
        }
        catch( IllegalArgumentException e )
        {
            throw new IllegalStateException( e );
        }
        catch( InvocationTargetException e )
        {
            throw new IllegalStateException( e );
        }
    }

}
