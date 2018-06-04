package com.sparta.is.core.network.gui;

import net.minecraft.entity.player.EntityPlayer;

public interface INumberReactor
{
    void onNumberReceived(double number, int id, EntityPlayer player);
}
