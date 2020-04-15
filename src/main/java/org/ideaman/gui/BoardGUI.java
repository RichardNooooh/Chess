package org.ideaman.gui;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.scene.control.*;
import org.ideaman.piece.Piece;



public class BoardGUI extends Application
{
    private Stage window;
    private BoardTile[][] tileButtons;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        createTiles();

        //The actual chess board should be on the boardStackPane
        GridPane boardGridPane = new GridPane();
        for (int i = 0; i < tileButtons.length; i++)
        {
            BoardTile[] rowTiles = tileButtons[i];
            for (int j = 0; j < tileButtons[i].length; j++)
            {
                BoardTile tile = rowTiles[j];

                Button tileButton = tile.getButton();
                boardGridPane.add(tileButton, j, i, 1, 1);
                tileButton.setLayoutX(100 * i);
                tileButton.setLayoutY(100 * j);
            }
        }

        Scene scene = new Scene(boardGridPane, 700, 700);
        window = primaryStage;
        window.setScene(scene);
        window.setTitle("Chess");
        window.show();
    }

    private void createTiles()
    {
        final int SIDE_LENGTH = Piece.Position.BOARD_LENGTH;
        tileButtons = new BoardTile[SIDE_LENGTH][SIDE_LENGTH];

        boolean isWhite = true;
        for (int i = 0; i < SIDE_LENGTH; i++)
        {
            for (int j = 0; j < SIDE_LENGTH; j++)
            {
                tileButtons[i][j] = new BoardTile(isWhite);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
    }
}
