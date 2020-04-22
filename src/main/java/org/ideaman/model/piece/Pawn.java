package org.ideaman.model.piece;

import org.ideaman.model.ChessManager;
import org.ideaman.utils.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Pawn extends Piece
{
	private boolean hasDoubleStepped = false;
	private ChessManager chessManager;

	private int doubleStepTurn;

	public Pawn(Position position, Side side, ChessManager chessManager)
	{
		type = PieceType.PAWN;
		pos = position;
		this.side = side;
		this.chessManager = chessManager;

		doubleStepTurn = -1;
	}

	@Override
	public Piece copy()
	{
		return new Pawn(new Position(pos.getX(), pos.getY()), side, chessManager);
	}

	@Override
	public List<Position> validMoveList (Piece[][] board)
	{
		LinkedList<Position> validPositions = new LinkedList<Position>();

		if (side == Side.WHITE)
			addPositions(board, validPositions, y -> (byte)(y - 1));
		else if (side == Side.BLACK)
			addPositions(board, validPositions, y -> (byte)(y + 1));

		return validPositions;
	}

	@Override
	public void move(Position newPosition, Piece[][] board)
	{
		hasMoved = true;
		byte newX = newPosition.getX();
		byte newY = newPosition.getY();

		board[pos.getX()][pos.getY()] = null;
		board[newX][newY] = this;

		if (newY - pos.getY() == 2 || newY - pos.getY() == -2)
		{
			hasDoubleStepped = true;
			doubleStepTurn = chessManager.getTurnCount();
		}

		int enPassantY = side == Side.WHITE ? newY + 1 : newY - 1;
		Piece enpassantPiece = board[newX][enPassantY];
		if (enpassantPiece instanceof Pawn && chessManager.getTurnCount() - ((Pawn) enpassantPiece).doubleStepTurn == 1)
			board[newX][enPassantY] = null;

		pos = new Position(newX, newY);
	}

	private void addPositions(Piece[][] board, List<Position> list, Function<Byte, Byte> moveFunction)
	{
		byte frontYPosition = moveFunction.apply(pos.getY());
		byte currentX = pos.getX();
		byte currentY = pos.getY();
		if (Position.isOnBoard(currentX, frontYPosition)) //TODO use Position.isOnBoard() instead
		{
			Piece frontPiece = board[currentX][frontYPosition];
			if (frontPiece == null)
				list.add(new Position(currentX, frontYPosition));

			if (!hasMoved)
			{
				byte doubleFrontYPosition = moveFunction.apply(frontYPosition);
				Piece doubleStepPiece = board[currentX][doubleFrontYPosition];
				if (frontPiece == null && doubleStepPiece == null)
					list.add(new Position(currentX, doubleFrontYPosition));
			}

			Piece frontRightPiece;
			if (currentX + 1 < Position.BOARD_LENGTH
					&& (frontRightPiece = board[currentX + 1][frontYPosition]) != null
					&& isEnemy(frontRightPiece))
				list.add(frontRightPiece.pos);

			Piece frontLeftPiece;
			if (currentX - 1 >= 0
					&& (frontLeftPiece = board[currentX - 1][frontYPosition]) != null
					&& isEnemy(frontLeftPiece))
				list.add(frontLeftPiece.pos);

			Piece enPassantRightPiece;
			if (currentX + 1 < Position.BOARD_LENGTH
					&& (enPassantRightPiece = board[currentX + 1][currentY]) != null
					&& isEnemy(enPassantRightPiece)
					&& enPassantRightPiece instanceof Pawn)
			{
				Pawn enPassantRightPawn = (Pawn) enPassantRightPiece;
				if (chessManager.getTurnCount() - enPassantRightPawn.doubleStepTurn == 1)
					list.add(new Position(currentX + 1, frontYPosition));
			}

			Piece enPassantLeftPiece;
			if (currentX - 1 >= 0
					&& (enPassantLeftPiece = board[currentX - 1][currentY]) != null
					&& isEnemy(enPassantLeftPiece)
					&& enPassantLeftPiece instanceof Pawn)
			{
				Pawn enPassantLeftPawn = (Pawn) enPassantLeftPiece;
				if (chessManager.getTurnCount() - enPassantLeftPawn.doubleStepTurn == 1)
					list.add(new Position(currentX - 1, frontYPosition));
			}
		}

	}

}
