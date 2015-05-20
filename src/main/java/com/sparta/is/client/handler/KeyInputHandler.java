package com.sparta.is.client.handler;

import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.reference.Key;
import com.sparta.is.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler
{
    private static Key getPressedKeyBinding()
    {
        if (KeyBindings.charge.isPressed())
        {
            return Key.CHARGE;
        }
        else if (KeyBindings.release.isPressed())
        {
            return Key.RELEASE;
        }
        else if (KeyBindings.standbyKey.isPressed())
        {
            return Key.STANDBY;
        }
        else if (KeyBindings.partialDeployKey.isPressed())
        {
            return Key.PARTIALDEPLOY;
        }
        else if (KeyBindings.fullDeployKey.isPressed())
        {
            return Key.FULLDEPLOY;
        }
        else
        {
            return Key.UNKNOWN;
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        LogHelper.info(getPressedKeyBinding());
    }

}
