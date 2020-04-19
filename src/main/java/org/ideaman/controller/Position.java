package org.ideaman.controller;

public class Position
{
	public static final int BOARD_LENGTH = 8; //TODO remove the boardlength var from other places.
	private byte x;
	private byte y;

	public Position(int x, int y)
	{
		this.x = (byte) x;
		this.y = (byte) y;
	}
	public byte getX(){ return x; }
	public byte getY(){ return y; }

	public static boolean isOnBoard(int x, int y)
	{
		return x >= 0
				&& y >= 0
				&& x < BOARD_LENGTH
				&& y < BOARD_LENGTH;
	}

	@Override
	public boolean equals(Object other)
	{
		if (other instanceof Position)
		{
			Position otherPos = (Position) other;
			return this.x == otherPos.x && this.y == otherPos.y;
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}