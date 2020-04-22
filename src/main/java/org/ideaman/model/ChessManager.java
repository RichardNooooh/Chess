package org.ideaman.model;

import org.ideaman.gui.GUIManager;
import org.ideaman.model.piece.*;
import org.ideaman.utils.Position;

import java.util.List;

public class ChessManager
{
	private GUIManager guiManager;
	private Piece[][] chessBoard;

	private int turnCount;

	private Side currentTurn;
	private Piece selectedPiece;
	private List<Position> validMoveList;

	public ChessManager(GUIManager manager)
	{
		guiManager = manager;
		startGame();
	}

	private void startGame()
	{
		currentTurn = Side.WHITE;
		chessBoard = new Piece[Position.BOARD_LENGTH][Position.BOARD_LENGTH];
		ChessSetup setup = ChessSetup.getInstance(this);
		Piece[] baseSetup = setup.getSetupPieces();
		for (Piece piece : baseSetup)
			chessBoard[piece.getPosition().getX()][piece.getPosition().getY()] = piece.copy();
	}

	public void selectPiece(Position position)
	{
		Piece newSelectedPiece = chessBoard[position.getX()][position.getY()];
		if (selectedPiece == null && newSelectedPiece != null && newSelectedPiece.getSide() == currentTurn)
		{
			validMoveList = newSelectedPiece.validMoveList(chessBoard);
			guiManager.setSelected(validMoveList);
			selectedPiece = newSelectedPiece;
		}
		else
		{
			if (validMoveList != null && validMoveList.contains(position))
			{
				selectedPiece.move(position, chessBoard);
				currentTurn = currentTurn == Side.WHITE ? Side.BLACK : Side.WHITE;
				turnCount++;
			}
			validMoveList = null;
			selectedPiece = null;
			guiManager.draw();
		}

//		if (selectedPiece != null && selectedPiece.getSide() == currentTurn) //TODO make sure this doesn't bring the king into check
//			selectedPiece.validMoveList(chessBoard);
	}

	public int getTurnCount()
	{
		return turnCount;
	}

	//TODO remove this after app is done, as this should only be used to draw the board
	public Piece[][] getChessBoard()
	{
		return chessBoard;
	}

	private static class ChessSetup
	{
		private static ChessManager chessManager;

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
			setupPieces[0] = new Pawn(new Position(0, 1), Side.BLACK, chessManager);
			setupPieces[1] = new Pawn(new Position(1, 1), Side.BLACK, chessManager);
			setupPieces[2] = new Pawn(new Position(2, 1), Side.BLACK, chessManager);
			setupPieces[3] = new Pawn(new Position(3, 1), Side.BLACK, chessManager);
			setupPieces[4] = new Pawn(new Position(4, 1), Side.BLACK, chessManager);
			setupPieces[5] = new Pawn(new Position(5, 1), Side.BLACK, chessManager);
			setupPieces[6] = new Pawn(new Position(6, 1), Side.BLACK, chessManager);
			setupPieces[7] = new Pawn(new Position(7, 1), Side.BLACK, chessManager);

			setupPieces[8] = new Pawn(new Position(0, 6), Side.WHITE, chessManager);
			setupPieces[9] = new Pawn(new Position(1, 6), Side.WHITE, chessManager);
			setupPieces[10] = new Pawn(new Position(2, 6), Side.WHITE, chessManager);
			setupPieces[11] = new Pawn(new Position(3, 6), Side.WHITE, chessManager);
			setupPieces[12] = new Pawn(new Position(4, 6), Side.WHITE, chessManager);
			setupPieces[13] = new Pawn(new Position(5, 6), Side.WHITE, chessManager);
			setupPieces[14] = new Pawn(new Position(6, 6), Side.WHITE, chessManager);
			setupPieces[15] = new Pawn(new Position(7, 6), Side.WHITE, chessManager);
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

		public static ChessSetup getInstance(ChessManager reference)
		{
			chessManager = reference;
			if (instance == null)
				instance = new ChessSetup();

			return instance;
		}
	}
}
