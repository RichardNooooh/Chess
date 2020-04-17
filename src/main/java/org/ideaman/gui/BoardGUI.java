package org.ideaman.gui;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.scene.control.*;
import org.ideaman.manager.ChessManager;
import org.ideaman.manager.Position;
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
        final int SIDE_LENGTH = Position.BOARD_LENGTH;
        tileButtons = new BoardTile[SIDE_LENGTH][SIDE_LENGTH];

        ChessManager manager = new ChessManager(tileButtons);
        Piece[][] chessBoard = manager.getChessBoard();
        GridPane boardGridPane = new GridPane();

        boolean isWhite = true;
        for (int i = 0; i < SIDE_LENGTH; i++)
        {
            for (int j = 0; j < SIDE_LENGTH; j++)
            {
                Piece connectedPiece = chessBoard[i][j];
                BoardTile newTile = new BoardTile(isWhite, connectedPiece, manager);
                tileButtons[i][j] = newTile;

                Button tileButton = newTile.getButton();
                boardGridPane.add(tileButton, i, j, 1, 1);
                tileButton.setLayoutX(100 * i);
                tileButton.setLayoutY(100 * j);

                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

        Scene scene = new Scene(boardGridPane, 700, 700);
        window = primaryStage;
        window.setScene(scene);
        window.setTitle("Chess");
        window.show();
    }

    private void createTiles()
    {

    }
}
