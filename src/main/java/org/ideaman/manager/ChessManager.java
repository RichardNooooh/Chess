package org.ideaman.manager;

import org.ideaman.gui.BoardTile;
import org.ideaman.piece.*;

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
		ChessSetup setup = ChessSetup.getInstance();
		Piece[] baseSetup = setup.getSetupPieces();
		for (Piece piece : baseSetup)
			chessBoard[piece.getPosition().getX()][piece.getPosition().getY()] = piece.copy();

	}

	public void select(Position position)
	{
		BoardTile selectedTile = guiBoard[position.getX()][position.getY()];
		System.out.println("This chess piece was selected: " + selectedPiece);
	}

	private static class ChessSetup
	{
		private static ChessSetup instance = null;
		private Piece[] setupPieces;

		private ChessSetup()
		{
			setupPieces = new Piece[32];
			setupPawns();
			setupBlackSpecial();
			setupWhiteSpecial();
		}

		private void setupPawns()
		{
			setupPieces[0] = new Pawn(new Position(0, 1), Side.BLACK);
			setupPieces[1] = new Pawn(new Position(1, 1), Side.BLACK);
			setupPieces[2] = new Pawn(new Position(2, 1), Side.BLACK);
			setupPieces[3] = new Pawn(new Position(3, 1), Side.BLACK);
			setupPieces[4] = new Pawn(new Position(4, 1), Side.BLACK);
			setupPieces[5] = new Pawn(new Position(5, 1), Side.BLACK);
			setupPieces[6] = new Pawn(new Position(6, 1), Side.BLACK);
			setupPieces[7] = new Pawn(new Position(7, 1), Side.BLACK);

			setupPieces[8] = new Pawn(new Position(0, 6), Side.WHITE);
			setupPieces[9] = new Pawn(new Position(1, 6), Side.WHITE);
			setupPieces[10] = new Pawn(new Position(2, 6), Side.WHITE);
			setupPieces[11] = new Pawn(new Position(3, 6), Side.WHITE);
			setupPieces[12] = new Pawn(new Position(4, 6), Side.WHITE);
			setupPieces[13] = new Pawn(new Position(5, 6), Side.WHITE);
			setupPieces[14] = new Pawn(new Position(6, 6), Side.WHITE);
			setupPieces[15] = new Pawn(new Position(7, 6), Side.WHITE);
		}

		private void setupBlackSpecial()
		{
			setupPieces[16] = new Rook(new Position(0, 0), Side.BLACK);
			setupPieces[17] = new Knight(new Position(1, 0), Side.BLACK);
			setupPieces[18] = new Bishop(new Position(2, 0), Side.BLACK);
			setupPieces[19] = new King(new Position(3, 0), Side.BLACK);
			setupPieces[20] = new Queen(new Position(4, 0), Side.BLACK);
			setupPieces[21] = new Bishop(new Position(5, 0), Side.BLACK);
			setupPieces[22] = new Knight(new Position(6, 0), Side.BLACK);
			setupPieces[23] = new Rook(new Position(7, 0), Side.BLACK);
		}

		private void setupWhiteSpecial()
		{
			setupPieces[24] = new Rook(new Position(0, 7), Side.WHITE);
			setupPieces[25] = new Knight(new Position(1, 7), Side.WHITE);
			setupPieces[26] = new Bishop(new Position(2, 7), Side.WHITE);
			setupPieces[27] = new Queen(new Position(4, 7), Side.WHITE);
			setupPieces[28] = new King(new Position(3, 7), Side.WHITE);
			setupPieces[29] = new Bishop(new Position(5, 7), Side.WHITE);
			setupPieces[30] = new Knight(new Position(6, 7), Side.WHITE);
			setupPieces[31] = new Rook(new Position(7, 7), Side.WHITE);
		}

		public Piece[] getSetupPieces()
		{
			return setupPieces;
		}

		public static ChessSetup getInstance()
		{
			if (instance == null)
				instance = new ChessSetup();

			return instance;
		}
	}
}
