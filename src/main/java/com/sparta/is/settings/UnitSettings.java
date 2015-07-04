package com.sparta.is.settings;

import com.sparta.is.utility.INBTTaggable;
import net.minecraft.nbt.NBTTagCompound;

public class UnitSettings implements INBTTaggable
{
    private int totalEqualizers;
    private int deployedState;
    private String ownerName, unitName;
    private final int MAX_EQUALIZERS = 8;

    public UnitSettings()
    {
        this(1, 0, "Unknown", "None");
    }

    public UnitSettings(int totalEqualizers, int deployedState, String ownerName, String unitName)
    {
        this.totalEqualizers = totalEqualizers;
        this.deployedState = deployedState;
        this.ownerName = ownerName;
        this.unitName = unitName;
    }

    public int getTotalEqualizers()
    {
        return totalEqualizers;
    }

    public void setTotalEqualizers(int totalEqualizers)
    {
        if ( totalEqualizers < 1 )
        {
            this.totalEqualizers = 1;
        }
        else if ( totalEqualizers > MAX_EQUALIZERS )
        {
            this.totalEqualizers = MAX_EQUALIZERS;
        }
        else
        {
            this.totalEqualizers = totalEqualizers;
        }
    }

    public int getDeployedState()
    {
        return deployedState;
    }

    public void setDeployedState(int deployedState)
    {
        if(deployedState != -1 && deployedState > -1)
        {
            this.deployedState = deployedState;
        }
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

    public String getUnitName()
    {
        return unitName;
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey("unit_settings") && nbtTagCompound.getTag("unit_settings").getId() == (byte) 10)
        {
            NBTTagCompound unitSettings = nbtTagCompound.getCompoundTag("unit_settings");

            if (unitSettings.hasKey("totalEqualizers"))
            {
                this.totalEqualizers = unitSettings.getInteger("totalEqualizers");

                if (this.totalEqualizers < 1)
                {
                    this.totalEqualizers = 1;
                }
                else if(this.totalEqualizers > MAX_EQUALIZERS)
                {
                    this.totalEqualizers = MAX_EQUALIZERS;
                }
            }
            else
            {
                this.totalEqualizers = 1;
            }

            if (unitSettings.hasKey("deployedState"))
            {
                this.deployedState = unitSettings.getInteger("deployedState");

                if (this.deployedState  == 0)
                {
                    this.deployedState = 0;
                }
                else if (this.deployedState == 1)
                {
                    this.deployedState = 1;
                }
                else if(this.deployedState == 2)
                {
                    this.deployedState = 2;
                }
            }
            else
            {
                this.deployedState = 0;
            }

            if(unitSettings.hasKey("ownerName"))
            {
                this.ownerName = unitSettings.getString("ownerName");

                if ( ownerName == null )
                {
                    this.ownerName = "Unknown";
                }
            }

            if(unitSettings.hasKey("unitName"))
            {
                this.unitName = unitSettings.getString("unitName");

                if(unitName == null)
                {
                    this.unitName = "None";
                }
            }

        }
        else
        {
            this.totalEqualizers = 1;
            this.deployedState = 0;
            this.ownerName = "Unknown";
            this.unitName = "None";
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagCompound unitSettings = new NBTTagCompound();
        unitSettings.setInteger("totalEqualizers", totalEqualizers);
        unitSettings.setInteger("deployedState", deployedState);
        unitSettings.setString("ownerName", ownerName);
        unitSettings.setString("unitName", unitName);
        nbtTagCompound.setTag("unit_settings", unitSettings);
    }

    @Override
    public String getTagLabel()
    {
        return this.getClass().getName();
    }
}
