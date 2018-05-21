package com.sparta.is.client.gui.component;

import com.sparta.is.entity.driveables.IControllable;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Mouse;

public class GuiDriveableController extends GuiScreen
{
    private IControllable plane;
    private boolean leftMouseHeld;
    private boolean rightMouseHeld;

    public GuiDriveableController(IControllable thePlane)
    {
        super();
        plane = thePlane;
    }

    @Override
    public void initGui()
    {
        if(mc.gameSettings.thirdPersonView == 1)
            mc.setRenderViewEntity(mc.player);
    }

    @Override
    public void onGuiClosed()
    {
        mc.mouseHelper.ungrabMouseCursor();
        mc.setRenderViewEntity(mc.player);
    }

    @Override
    public void handleMouseInput()
    {
        EntityPlayer player = (EntityPlayer)plane.getControllingEntity();
        if(player != mc.player)
        {
            mc.displayGuiScreen(null);
            return;
        }

        int dWheel = Mouse.getDWheel();
        if(dWheel != 0)
        {
            player.inventory.changeCurrentItem(dWheel);
        }

        //Right mouse. Fires shells, drops bombs. Is not a holding thing
        if(Mouse.isButtonDown(1))
            plane.pressKey(8, player);

        if(!leftMouseHeld&& Mouse.isButtonDown(0)) //Left mouse, for MGs. Is a holding thing
        {
            leftMouseHeld = true;
            plane.updateKeyHeldState(9, true);
        }
        if(leftMouseHeld && !Mouse.isButtonDown(0))
        {
            leftMouseHeld = false;
            plane.updateKeyHeldState(9, false);
        }
        if(!rightMouseHeld && Mouse.isButtonDown(1)) //Right mouse
        {
            rightMouseHeld = true;
            plane.updateKeyHeldState(8, true);
        }
        if(rightMouseHeld && !Mouse.isButtonDown(1))
        {
            rightMouseHeld = false;
            plane.updateKeyHeldState(8, false);
        }
    }

    @Override
    protected void keyTyped(char c, int i)
    {
        if(i == 1)
        {
            mc.displayGuiScreen(null);
            mc.displayInGameMenu();
        }
        if(i == 59)
        {
            mc.gameSettings.hideGUI = !mc.gameSettings.hideGUI;
        }
        if(i == 61)
        {
            mc.gameSettings.showDebugInfo = !mc.gameSettings.showDebugInfo;
        }
        if(i == 63)
        {
            mc.gameSettings.thirdPersonView = (mc.gameSettings.thirdPersonView + 1) % 3;
            if(mc.gameSettings.thirdPersonView == 1)
                mc.setRenderViewEntity((plane.getCamera() == null ? mc.player : plane.getCamera()));
            else mc.setRenderViewEntity(mc.player);
        }
        if(i == 66)
        {
            mc.gameSettings.smoothCamera = !mc.gameSettings.smoothCamera;
        }
        if(i == mc.gameSettings.keyBindInventory.getKeyCode())
        {
            mc.displayGuiScreen(new GuiInventory(mc.player));
        }
        if(i == mc.gameSettings.keyBindDrop.getKeyCode())
        {
            //mc.thePlayer.dropCurrentItem();
        }
        if(i == mc.gameSettings.keyBindChat.getKeyCode())
        {
            mc.displayGuiScreen(new GuiChat());
        }
        if(i == mc.gameSettings.keyBindCommand.getKeyCode())
        {
            mc.displayGuiScreen(new GuiChat("/"));
        }
    }

    @Override
    public void updateScreen()
    {
        if(mc.gameSettings.thirdPersonView == 1)
            mc.setRenderViewEntity(mc.player);
        else mc.setRenderViewEntity(mc.player);
    }

    @Override
    public void drawBackground(int i)
    {
        //Plane gauges overlay
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

}
