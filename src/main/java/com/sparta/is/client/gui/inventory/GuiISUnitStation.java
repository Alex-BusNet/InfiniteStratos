package com.sparta.is.client.gui.inventory;

import com.sparta.is.client.gui.base.GuiBase;
import com.sparta.is.client.gui.base.GuiColor;
import com.sparta.is.inventory.ContainerISUnitStation;
import com.sparta.is.core.reference.Textures;
import com.sparta.is.tileentity.TileEntityISUnitStation;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiISUnitStation extends GuiBase
{
    private TileEntityISUnitStation tileEntityISUnitStation;
    private String unitName = "";

    public GuiISUnitStation(InventoryPlayer inventoryPlayer, TileEntityISUnitStation tileEntityISUnitStation)
    {
        super(new ContainerISUnitStation(inventoryPlayer, tileEntityISUnitStation));
        this.tileEntityISUnitStation = tileEntityISUnitStation;
        xSize = 188;
        ySize = 199;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        setUnitInformation();
        fontRenderer.drawString(unitName, 55, 7, new GuiColor(0, 0, 0).getColor());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.getTextureManager().bindTexture(Textures.Gui.IS_UNIT_STATION_GUI);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

    }

    private void setUnitInformation()
    {
        LogHelper.info("setUnitInformation called");
        unitName = tileEntityISUnitStation.getUnitName();
    }
}
