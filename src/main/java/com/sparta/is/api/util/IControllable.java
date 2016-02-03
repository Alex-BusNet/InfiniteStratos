package com.sparta.is.api.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public interface IControllable
{
    public void onMouseMoved(int deltaX, int deltaY);

    public boolean pressKey(int key, EntityPlayer player);

    public void updateKeyHeldState(int key, boolean held);

    public Entity getControllingEntity();

    public boolean isDead();

    public float getPlayerRoll();
    public float getPrevPlayerRoll();

    public float getCameraDistance();

    @SideOnly(Side.CLIENT)
    public EntityLivingBase getCamera();

}
