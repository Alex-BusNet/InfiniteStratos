package com.sparta.is.client.render.model;

import com.sparta.is.glUtils.TessellatorModel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelElucidator
{
    private TessellatorModel modelElucidator;

    public ModelElucidator()
    {
        modelElucidator = new TessellatorModel("/assets/is/models/elucidator.obj");
    }

    public void render()
    {
        modelElucidator.render();
    }

}
