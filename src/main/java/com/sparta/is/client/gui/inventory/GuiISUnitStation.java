package com.sparta.is.client.gui.inventory;

import com.sparta.is.inventory.ContainerISUnitStation;
import com.sparta.is.reference.Textures;
import com.sparta.is.tileentity.TileEntityISStation;
import com.sparta.is.utility.LogHelper;
import com.sparta.repackage.cofh.lib.gui.GuiBase;
import com.sparta.repackage.cofh.lib.gui.GuiColor;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiISUnitStation extends GuiBase
{
    private TileEntityISStation tileEntityISStation;
    private String unitName = "IS Test Unit";

    public GuiISUnitStation(InventoryPlayer inventoryPlayer, TileEntityISStation tileEntityISStation)
    {
        super(new ContainerISUnitStation(inventoryPlayer, tileEntityISStation));
        this.tileEntityISStation = tileEntityISStation;
        xSize = 188;
        ySize = 199;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        setUnitInformation();
        fontRendererObj.drawString(unitName, 55, 7, new GuiColor(0, 0, 0).getColor());
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

        if(tileEntityISStation.isStandNearby())
        {
            LogHelper.info("Stand nearby. Getting Unit Info");
            tileEntityISStation.getUnitName();
        }
        else
        {
            unitName = "Test Unit";
        }
    }
}
