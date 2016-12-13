package com.sparta.is.settings;

import com.sparta.is.utils.INBTTaggable;
import net.minecraft.nbt.NBTTagCompound;

public class OneOffSettings implements INBTTaggable
{
    private int oneOffActive;

    public OneOffSettings()
    {
        this(false);
    }

    public OneOffSettings(boolean oneOffActive)
    {
        if(oneOffActive)
            this.oneOffActive = 1;
        else
            this.oneOffActive = 0;
    }

    public boolean isOneOffActive()
    {
        if(oneOffActive == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setOneOff(int oneOffActive)
    {
        this.oneOffActive = oneOffActive;
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if(nbtTagCompound != null && nbtTagCompound.hasKey("oneOff_settings") && nbtTagCompound.getTag("oneOff_settings").getId() == (byte) 10)
        {
            NBTTagCompound oneOffSettings = nbtTagCompound.getCompoundTag("oneOff_settings");

            if(oneOffSettings.hasKey("oneOffActive"))
            {
                this.oneOffActive = oneOffSettings.getInteger("oneOffActive");

                if(oneOffActive == 1)
                {
                    this.oneOffActive = 1;
                }
                else
                {
                    this.oneOffActive = 0;
                }
            }
            else
            {
                this.oneOffActive = 0;
            }
        }
        else
        {
            this.oneOffActive = 0;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagCompound oneOffSettings = new NBTTagCompound();
        oneOffSettings.setInteger("oneOffActive", oneOffActive);
        nbtTagCompound.setTag("oneOff_settings", oneOffSettings);
    }

    @Override
    public String getTagLabel()
    {
        return this.getClass().getName();
    }
}
