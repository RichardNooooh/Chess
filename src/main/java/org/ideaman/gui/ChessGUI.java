package org.ideaman.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.ideaman.utils.Position;

public class ChessGUI
{
	GUIManager guiManager;

	TileGUI[][] boardGUI;
	Stage primaryStage;

	public ChessGUI(Stage stage, GUIManager guiManager)
	{
		this.guiManager = guiManager;
		primaryStage = stage;
		setupBoard();
	}

	protected TileGUI getTileGUI(Position position)
	{
		return boardGUI[position.getX()][position.getY()];
	}

	private void setupBoard()
	{
		final int SIDE_LENGTH = Position.BOARD_LENGTH;
		boardGUI = new TileGUI[SIDE_LENGTH][SIDE_LENGTH];

		GridPane gridPane = new GridPane();

		Scene scene = new Scene(gridPane, 700, 700);

		ClassLoader cl = ClassLoader.getSystemClassLoader();
		String css = cl.getResource("tile.css").toExternalForm();
		scene.getStylesheets().add(css);

		boolean isWhiteColored = true;
		for (int i = 0; i < SIDE_LENGTH; i++)
		{
			for (int j = 0; j < SIDE_LENGTH; j++)
			{
				TileGUI chessTile = new TileGUI(i, j, guiManager);
				Button chessButton = chessTile.getButton();

				chessButton.getStyleClass().add(isWhiteColored ? "white" : "black");
				boardGUI[i][j] = chessTile;
				gridPane.add(chessButton, i, j, 1, 1);
				isWhiteColored = !isWhiteColored;
			}
			isWhiteColored = !isWhiteColored;
		}

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
