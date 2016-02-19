package net.minecraft.client.gui.spectator;

import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public interface ISpectatorMenuView
{
    List<ISpectatorMenuObject> func_178669_a();

    IChatComponent func_178670_b();
}