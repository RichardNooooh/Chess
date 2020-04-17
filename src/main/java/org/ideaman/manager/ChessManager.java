package org.ideaman.manager;

import org.ideaman.gui.BoardTile;
import org.ideaman.piece.Piece;

public class ChessManager
{
	private Piece[][] chessBoard;
	private BoardTile[][] guiBoard;
	private Piece selectedPiece;

	public ChessManager(BoardTile[][] guiBoard)
	{
		this.guiBoard = guiBoard;
		startGame();
	}

	public Piece[][] getChessBoard()
	{
		return chessBoard;
	}

	private void startGame()
	{
		chessBoard = new Piece[Position.BOARD_LENGTH][Position.BOARD_LENGTH];
		//place pieces in correct positions
	}

	public void select(Position position)
	{
		BoardTile selectedTile = guiBoard[position.getX()][position.getY()];
		System.out.println("This chess piece was selected: " + selectedPiece);
	}

}
