public abstract class Piece
{
	private PieceType type;
	private Position pos;
	private Side side;

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
