package com.sparta.is.client.render.model;

import com.sparta.is.reference.Models;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelISUnitStation
{
    private IModelCustom modelISUnitStation;

    public ModelISUnitStation()
    {
        modelISUnitStation = AdvancedModelLoader.loadModel(Models.IS_UNIT_STATION);
    }

    public void render()
    {
        modelISUnitStation.renderAll();
    }
}
