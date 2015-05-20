package com.sparta.is.client.settings;

import com.sparta.is.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings
{
    public static KeyBinding charge = new KeyBinding(Names.Keys.CHARGE, Keyboard.KEY_M, Names.Keys.CATEGORY);
    public static KeyBinding release = new KeyBinding(Names.Keys.RELEASE, Keyboard.KEY_R, Names.Keys.CATEGORY);

    public static KeyBinding standbyKey = new KeyBinding(Names.Keys.STANDBY, Keyboard.KEY_C, Names.Keys.CATEGORY);
    public static KeyBinding partialDeployKey = new  KeyBinding(Names.Keys.PARTIALDEPLOY, Keyboard.KEY_V, Names.Keys.CATEGORY);
    public static KeyBinding fullDeployKey = new KeyBinding(Names.Keys.FULLDEPLOY,Keyboard.KEY_X, Names.Keys.CATEGORY);

}
