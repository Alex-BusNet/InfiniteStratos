package com.sparta.is.client.gui.inventory;

import com.sparta.is.inventory.ContainerUnitStand;
import com.sparta.is.reference.Textures;
import com.sparta.is.tileentity.TileEntityUnitStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiUnitStand extends GuiContainer
{
    private TileEntityUnitStand tileEntityUnitStand;

    public GuiUnitStand(InventoryPlayer inventoryPlayer, TileEntityUnitStand tileEntityUnitStand)
    {
        super(new ContainerUnitStand(inventoryPlayer, tileEntityUnitStand));
        this.tileEntityUnitStand = tileEntityUnitStand;
        xSize = 188;
        ySize = 199;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        //NOOP
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.UNIT_STAND_GUI);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
