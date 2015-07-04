package com.sparta.is.client.settings;

import com.sparta.is.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings
{
    public static KeyBinding standbyKey = new KeyBinding(Names.Keys.STANDBY, Keyboard.KEY_C, Names.Keys.CATEGORY);
    public static KeyBinding partialDeployKey = new  KeyBinding(Names.Keys.PARTIAL_DEPLOY, Keyboard.KEY_V, Names.Keys.CATEGORY);
    public static KeyBinding fullDeployKey = new KeyBinding(Names.Keys.FULL_DEPLOY, Keyboard.KEY_X, Names.Keys.CATEGORY);
    public static KeyBinding equalizerAccessModifier = new KeyBinding(Names.Keys.EQUALIZER_ACCESS_MODIFIER, Keyboard.KEY_LMENU, Names.Keys.CATEGORY);
    public static KeyBinding oneOffAbility = new KeyBinding(Names.Keys.ONE_OFF_ABILITY, Keyboard.KEY_F, Names.Keys.CATEGORY);
    public static KeyBinding oneOffAbilityOff = new KeyBinding(Names.Keys.ONE_OFF_ABILITY_OFF, Keyboard.KEY_B, Names.Keys.CATEGORY);

}
