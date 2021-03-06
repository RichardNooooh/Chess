package org.ideaman.model.piece;

import org.ideaman.utils.Position;

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

		board[pos.getX()][pos.getY()] = null;
		board[newX][newY] = this;

		pos = new Position(newX, newY);
	}

	public abstract Piece copy();

	public PieceType getType(){ return type; }

	public Position getPosition()
	{
		return pos;
	}

	public Side getSide() { return side; }

	public boolean isEnemy(Piece otherPiece)
	{
		return this.side != otherPiece.side;
	}

	public String toString()
	{
		return "Piece: " + side + " " + type + " at " + pos;
	}


}
