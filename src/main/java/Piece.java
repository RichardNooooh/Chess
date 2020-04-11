public abstract class Piece
{
	protected PieceType type;
	protected Position pos;
	protected Side side;

	protected boolean canMove;
	protected Position previousCheckedPosition;

	public abstract boolean canMove(Position newPosition, Piece[][] board);

	public void move(Position newPosition, Piece[][] board)
	{
		if (canMove && newPosition.equals(previousCheckedPosition))
		{
			byte newX = newPosition.getX();
			byte newY = newPosition.getY();

			pos = new Position(newX, newY);
			board[pos.getX()][pos.getX()] = null;
			board[newX][newY] = this;
		}
		else
			throw new IllegalStateException("You must first check the position of the piece, and if that is true, " +
					"call this method with the same position.");
	}

	public boolean isEnemy(Piece otherPiece)
	{
		return this.side != otherPiece.side;
	}

	public class Position
	{
		private byte x;
		private byte y;

		public Position(byte x, byte y)
		{
			this.x = x;
			this.y = y;
		}
		public byte getX(){ return x; }
		public byte getY(){ return y; }

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
	}
}
