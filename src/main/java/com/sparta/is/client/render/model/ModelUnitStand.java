package com.sparta.is.client.render.model;

import com.sparta.is.reference.Models;
import com.sparta.is.utility.LogHelper;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelUnitStand
{
    private IModel modelUnitStand;

    public ModelUnitStand()
    {
        try
        {
            modelUnitStand = OBJLoader.INSTANCE.loadModel(Models.UNIT_STAND);
        }
        catch ( Exception e )
        {
            LogHelper.error("Model Unit Stand Load Error");
        }
    }

//    public void render()
//    {
//        modelUnitStand.renderAll();
//    }
}
