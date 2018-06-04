package com.sparta.is.core.network.gui;

import net.minecraft.entity.player.EntityPlayer;

public interface IStringReactor
{
    void onTextReceived(String text, int textID, EntityPlayer player);
}
