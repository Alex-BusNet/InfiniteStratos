package com.sparta.is.client.settings;

import com.sparta.is.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings
{
    //TODO: Change Deploy State to toggle key
    public static final KeyBinding STANDBY          = new KeyBinding(Names.Keys.STANDBY, Keyboard.KEY_X, Names.Keys.CATEGORY);
    public static final KeyBinding PARTIAL_DEPLOY   = new KeyBinding(Names.Keys.PARTIAL_DEPLOY, Keyboard.KEY_C, Names.Keys.CATEGORY);
    public static final KeyBinding FULL_DEPLOY      = new KeyBinding(Names.Keys.FULL_DEPLOY, Keyboard.KEY_V, Names.Keys.CATEGORY);
    public static final KeyBinding EQUALIZER_ACCESS = new KeyBinding(Names.Keys.EQUALIZER_ACCESS_MODIFIER, Keyboard.KEY_LMENU, Names.Keys.CATEGORY);
    public static final KeyBinding ONE_OFF          = new KeyBinding(Names.Keys.ONE_OFF_ABILITY, Keyboard.KEY_G, Names.Keys.CATEGORY);
}
