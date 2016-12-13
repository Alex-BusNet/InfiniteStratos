package com.sparta.is.settings;

import com.sparta.is.utils.INBTTaggable;
import net.minecraft.nbt.NBTTagCompound;

public class UnitSettings implements INBTTaggable
{
    private int TotalEqualizers;
    private int DeployedState;
    private String OwnerName, UnitName;
    private final int MAX_EQUALIZERS = 8;

    public UnitSettings()
    {
        this(1, 0, "Unknown", "None");
    }

    public UnitSettings(int totalEqualizers, int deployedState, String ownerName, String unitName)
    {
        TotalEqualizers = totalEqualizers;
        DeployedState = deployedState;
        OwnerName = ownerName;
        UnitName =  unitName;
    }

    public int getTotalEqualizers()
    {
        return TotalEqualizers;
    }

    public void setTotalEqualizers(int totalEqualizers)
    {
        if(totalEqualizers < 1)
        {
            TotalEqualizers = MAX_EQUALIZERS;
        }
        else if(totalEqualizers > MAX_EQUALIZERS)
        {
            TotalEqualizers = MAX_EQUALIZERS;
        }
        else
        {
            TotalEqualizers = totalEqualizers;
        }
    }

    public int getDeployedState()
    {
        return DeployedState;
    }

    public void setDeployedState(int state)
    {
        if(state != -1 && state > -1)
        {
            DeployedState = state;
        }
    }

    public String getOwnerName()
    {
        return OwnerName;
    }

    public void setOwnerName(String name)
    {
        OwnerName = name;
    }

    public String getUnitName()
    {
        return UnitName;
    }

    public void setUnitName(String name)
    {
        UnitName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if(nbtTagCompound != null && nbtTagCompound.hasKey("unitSettings"))
        {
            NBTTagCompound unitSettings = nbtTagCompound.getCompoundTag("unitSettings");

            if(unitSettings.hasKey("totalEqualizers"))
            {
                TotalEqualizers = unitSettings.getInteger("totalEqualizers");
            }
            else
            {
                TotalEqualizers = 1;
            }

            if(unitSettings.hasKey("deployedState"))
            {
                DeployedState = unitSettings.getInteger("deployedState");
            }
            else
            {
                //Standby state
                DeployedState = 0;
            }

            if(unitSettings.hasKey("ownerName"))
            {
                OwnerName = unitSettings.getString("ownerName");
            }
            else
            {
                OwnerName = "Unknown";
            }

            if(unitSettings.hasKey("unitName"))
            {
                UnitName = unitSettings.getString("unitName");
            }
            else
            {
                UnitName = "None";
            }
        }
        else
        {
            TotalEqualizers = 1;
            DeployedState = 0;
            OwnerName = "Unknown";
            UnitName = "None";
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagCompound unitSettings = new NBTTagCompound();
        unitSettings.setInteger("totalEqualizers", TotalEqualizers);
        unitSettings.setInteger("deployedState", DeployedState);
        unitSettings.setString("ownerName", OwnerName);
        unitSettings.setString("unitName", UnitName);

        nbtTagCompound.setTag("unitSettings", unitSettings);
    }

    @Override
    public String getTagLabel()
    {
        return this.getClass().getName();
    }
}
