package org.ideaman.model.piece;

import org.ideaman.utils.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Pawn extends Piece
{
	private boolean doubleStepped = false;

	public Pawn(Position position, Side side)
	{
		type = PieceType.PAWN;
		pos = position;
		this.side = side;
	}

	@Override
	public Piece copy()
	{
		return new Pawn(new Position(pos.getX(), pos.getY()), side);
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

		if (newX - pos.getX() == 2 || newX - pos.getX() == -2)
			doubleStepped = true;

		pos = new Position(newX, newY);
//		Piece enpassantPiece = board[newX][newY - 1];
//		if (enpassantPiece instanceof Pawn && ((Pawn) enpassantPiece).hasDoubleStepped())
//			board[newX][newY - 1] = null;
	}

	public boolean hasDoubleStepped() {return doubleStepped;}
	public void resetDoubleStep() { doubleStepped = false;}

	private void addPositions(Piece[][] board, List<Position> list, Function<Byte, Byte> moveFunction)
	{
		byte frontYPosition = moveFunction.apply(pos.getY());
		byte currentX = pos.getX();
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
		}

	}

}
