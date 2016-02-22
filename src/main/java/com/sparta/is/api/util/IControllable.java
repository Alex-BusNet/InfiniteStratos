package com.sparta.is.api.util;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
