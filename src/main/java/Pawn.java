public class Pawn extends Piece
{
//	private boolean canMove;

	public Pawn(Position position)
	{
		type = PieceType.PAWN;
		pos = position;
	}

	@Override
	public boolean canMove(Position newPosition, Piece[][] board)
	{
		byte thisX = pos.getX();
		byte thisY = pos.getY();
		byte newX = newPosition.getX();
		byte newY = newPosition.getY();

		int xDistance = newX - thisX;
		int yDistance = newY - thisY;

		boolean isInFront = (side == Side.WHITE && yDistance == 1) || (side == Side.BLACK && yDistance == -1);

		//TODO I could probably condense this with lambda expressions
		if (isInFront)
		{
			if (xDistance == 0)
			{
				Piece frontPiece = side == Side.WHITE ? board[thisX][thisY + 1] : board[thisX][thisY - 1];
				return frontPiece == null;
			}
			else if (xDistance == 1)
			{
				Piece sidePiece = side == Side.WHITE ? board[thisX + 1][thisY + 1] : board[thisX + 1][thisY - 1];
				return sidePiece != null;
			}
			else if (xDistance == -1)
			{
				Piece sidePiece = side == Side.WHITE ? board[thisX - 1][thisY + 1] : board[thisX - 1][thisY - 1];
				return sidePiece != null;
			}
		}

		return false;
	}


	@Override
	public void move(Position newPosition, Piece[][] board)
	{
		//should throw an exception if canMove is false
	}

}
