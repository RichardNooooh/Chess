package org.ideaman.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ideaman.model.ChessManager;

public class GUIManager
{
	ChessGUI chessGUI;
	ChessManager chessManager;

	public GUIManager(Stage stage)
	{
		chessGUI = new ChessGUI(stage);
		chessManager = new ChessManager();
	}

	//TODO adds the top StackPane image as the circle
	//protected void setAsSelected(Position)

	//TODO removes the top StackPane image, but not the piece image if there is one
	//protected void setAsNotSelected(Position)

	//TODO adds the piece image to the stackpane; make sure there is no circle selection
	//protected void setChessPieceImage()
}
