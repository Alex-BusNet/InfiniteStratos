package com.sparta.is.client.render.model;

import com.sparta.is.reference.Models;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelISUnitStation
{
    private ICustomModelLoader modelISUnitStation;

    public ModelISUnitStation()
    {
        modelISUnitStation = OBJLoader.instance.loadModel(Models.IS_UNIT_STATION);
    }

    public void render()
    {
        modelISUnitStation.renderAll();
    }
}
