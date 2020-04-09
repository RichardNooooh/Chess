public class Pawn extends Piece
{

	public Pawn(Position position)
	{
		type = PieceType.PAWN;
		pos = position;
	}

	@Override
	public boolean canMove(Position newPosition, Piece[][] board)
	{
		canMove = true;
		previousCheckedPosition = newPosition;

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
				return canMove = frontPiece == null;
			}
			else if (xDistance == 1)
			{
				Piece sidePiece = side == Side.WHITE ? board[thisX + 1][thisY + 1] : board[thisX + 1][thisY - 1];
				return canMove = sidePiece != null;
			}
			else if (xDistance == -1)
			{
				Piece sidePiece = side == Side.WHITE ? board[thisX - 1][thisY + 1] : board[thisX - 1][thisY - 1];
				return canMove = sidePiece != null;
			}
		}
		canMove = false;
		return false;
	}

}
