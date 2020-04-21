package org.ideaman.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.ideaman.utils.Position;

public class ChessGUI
{
	ChessTileGUI[][] boardGUI;
	Stage primaryStage;

	public ChessGUI(Stage stage)
	{
		primaryStage = stage;
		setupBoard();
	}

	private void setupBoard()
	{
		final int SIDE_LENGTH = Position.BOARD_LENGTH;
		boardGUI = new ChessTileGUI[SIDE_LENGTH][SIDE_LENGTH];

		GridPane gridPane = new GridPane();

		boolean isWhiteColored = false;
		for (int i = 0; i < SIDE_LENGTH; i++)
		{
			for (int j = 0; j < SIDE_LENGTH; j++)
			{
				ChessTileGUI chessTile = new ChessTileGUI(isWhiteColored, i, j);
				Button chessButton = chessTile.getButton();

				boardGUI[i][j] = chessTile;
				gridPane.add(chessButton, i, j, 1, 1);
				isWhiteColored = !isWhiteColored;
			}
			isWhiteColored = !isWhiteColored;
		}
		Scene scene = new Scene(gridPane, 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
