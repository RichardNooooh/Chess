public abstract class Piece
{
	protected PieceType type;
	protected Position pos;
	protected Side side;

	public abstract boolean canMove(Position newPosition, Piece[][] board);

	public abstract void move(Position newPosition, Piece[][] board);

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
	}
}
