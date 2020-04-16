package org.ideaman.piece;

import org.ideaman.manager.Position;

import java.util.List;

public abstract class Piece
{
	protected PieceType type;
	protected Position pos;
	protected Side side;

	protected boolean hasMoved;

	public abstract List<Position> validMoveList (Piece[][] board);

	public void move(Position newPosition, Piece[][] board)
	{
		hasMoved = true;
		byte newX = newPosition.getX();
		byte newY = newPosition.getY();


		pos = new Position(newX, newY);
		board[pos.getX()][pos.getY()] = null;
		board[newX][newY] = this;
	}

	public Position getPosition()
	{
		return pos;
	}

	public boolean isEnemy(Piece otherPiece)
	{
		return this.side != otherPiece.side;
	}

	public String toString()
	{
		return "Piece: " + side + " " + type + " at " + pos;
	}


}
