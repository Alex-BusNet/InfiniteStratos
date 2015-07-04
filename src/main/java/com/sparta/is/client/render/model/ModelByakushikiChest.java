package com.sparta.is.client.render.model;

import com.sparta.is.glUtils.TessellatorModel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelByakushikiChest
{
    private TessellatorModel modelByakushikiChest;

    public ModelByakushikiChest()
    {
        modelByakushikiChest = new TessellatorModel("/assets/is/models/ByakushikiChest.obj");
    }

    public void render()
    {
        modelByakushikiChest.render();
    }
}
