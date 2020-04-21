package org.ideaman.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class ChessTileGUI
{
	StackPane layeredImages;
	Button tileButton;

	boolean isWhiteColored;

	protected ChessTileGUI(boolean isWhiteColored, int x, int y)
	{
		tileButton = new Button();
		tileButton.setLayoutX(100 * x);
		tileButton.setLayoutY(100 * y);
	}

	protected Button getButton()
	{
		return tileButton;
	}


}
