package com.sparta.is.client.render.model;

import com.sparta.is.reference.Models;
import com.sparta.is.utility.LogHelper;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelISUnitStation
{
    private IModel modelISUnitStation;

    public ModelISUnitStation()
    {
        try
        {
            modelISUnitStation = OBJLoader.INSTANCE.loadModel(Models.IS_UNIT_STATION);
        }
        catch ( Exception e )
        {
            LogHelper.error("Model IS Unit Station Load Error");
        }
    }

//    public void render()
//    {
//        modelISUnitStation.renderAll();
//    }
}
