package com.sparta.is.client.itemInfo;

import com.sparta.is.item.ItemISCore;

import java.util.List;

public class CoreInfo
{
    public void addCorInformation(String coreNumber, List CoreDescription)
    {
        for (ItemISCore.ReservedCores core : ItemISCore.ReservedCores.values())
        {
            if (core == ItemISCore.ReservedCores.Core_001)
                CoreDescription.add("Byakushiki");

        }

    }
}
