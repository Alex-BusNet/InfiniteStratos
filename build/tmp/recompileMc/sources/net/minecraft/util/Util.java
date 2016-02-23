package net.minecraft.util;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Util
{
    @SideOnly(Side.CLIENT)
    public static Util.EnumOS getOSType()
    {
        String s = System.getProperty("os.name").toLowerCase();
        return s.contains("win") ? Util.EnumOS.WINDOWS : (s.contains("mac") ? Util.EnumOS.OSX : (s.contains("solaris") ? Util.EnumOS.SOLARIS : (s.contains("sunos") ? Util.EnumOS.SOLARIS : (s.contains("linux") ? Util.EnumOS.LINUX : (s.contains("unix") ? Util.EnumOS.LINUX : Util.EnumOS.UNKNOWN)))));
    }

    public static <V> V runTask(FutureTask<V> task, Logger logger)
    {
        try
        {
            task.run();
            return task.get();
        }
        catch (ExecutionException executionexception)
        {
            logger.fatal((String)"Error executing task", (Throwable)executionexception);
        }
        catch (InterruptedException interruptedexception)
        {
            logger.fatal((String)"Error executing task", (Throwable)interruptedexception);
        }

        return (V)null;
    }

    @SideOnly(Side.CLIENT)
    public static enum EnumOS
    {
        LINUX,
        SOLARIS,
        WINDOWS,
        OSX,
        UNKNOWN;
    }
}