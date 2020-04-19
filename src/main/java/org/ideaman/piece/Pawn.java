package org.ideaman.piece;

import org.ideaman.manager.Position;

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

		pos = new Position(newX, newY);
		board[pos.getX()][pos.getY()] = null;
		board[newX][newY] = this;

		if (newX - pos.getX() == 2 || newX - pos.getX() == -2)
			doubleStepped = true;

		Piece enpassantPiece = board[newX][newY - 1];
		if (enpassantPiece instanceof Pawn && ((Pawn) enpassantPiece).hasDoubleStepped())
			board[newX][newY - 1] = null;
	}

	public boolean hasDoubleStepped() {return doubleStepped;}
	public void resetDoubleStep() { doubleStepped = false;}

	private void addPositions(Piece[][] board, List<Position> list, Function<Byte, Byte> moveFunction)
	{
		byte frontYPosition = moveFunction.apply(pos.getY());
		byte currentX = pos.getX();
		if (frontYPosition < Position.BOARD_LENGTH)
		{
			Piece frontPiece = board[currentX][frontYPosition];
			if (frontPiece != null)
				list.add(frontPiece.pos);

			if (!hasMoved)
			{
				Piece doubleStepPiece = board[currentX][moveFunction.apply(frontYPosition)];
				if (frontPiece != null && doubleStepPiece != null)
					list.add(doubleStepPiece.pos);
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
