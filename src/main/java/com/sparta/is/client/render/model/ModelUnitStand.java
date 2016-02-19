package com.sparta.is.client.render.model;

import com.sparta.is.reference.Models;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelUnitStand
{
    private IModelCustom modelUnitStand;

    public ModelUnitStand()
    {
        modelUnitStand = AdvancedModelLoader.loadModel(Models.UNIT_STAND);
    }

    public void render()
    {
        modelUnitStand.renderAll();
    }
}
