package com.sparta.repackage.cofh.lib.gui.element;

import com.sparta.repackage.cofh.lib.gui.GuiBase;

public class ElementSimpleBox extends ElementBase {

	protected int color;

	public ElementSimpleBox(GuiBase gui, int posX, int posY, Number color) {

		this(gui, posX, posY, 16, 16, color);
	}

	public ElementSimpleBox(GuiBase gui, int posX, int posY, int width, int height, Number color) {

		super(gui, posX, posY, width, height);
		setColor(color);
	}

	public ElementSimpleBox setColor(Number color) {

		this.color = color.intValue();
		return this;
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks) {

		drawSizedModalRect(posX, posY, posX + sizeX, posY + sizeY, color);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY) {

		return;
	}

}