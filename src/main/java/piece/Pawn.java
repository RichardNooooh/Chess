package piece;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Pawn extends Piece
{
//	private boolean hasMoved;

	public Pawn(Position position, Side side)
	{
		type = PieceType.PAWN;
		pos = position;
		this.side = side;
	}

	@Override
	public List<Position> validMoveList (Piece[][] board)
	{
		LinkedList<Position> validPositions = new LinkedList<Position>();

		if (side == Side.WHITE)
			addPositions(board, validPositions, y -> (byte)(y + 1));
		else if (side == Side.BLACK)
			addPositions(board, validPositions, y -> (byte)(y - 1));

		return validPositions;
	}

	private void addPositions(Piece[][] board, List<Position> list, Function<Byte, Byte> moveFunction)
	{
		byte frontYPosition = moveFunction.apply(pos.getY());
		byte currentX = pos.getX();

		Piece frontPiece = board[currentX][frontYPosition];
		Piece frontRightPiece = board[currentX + 1][frontYPosition];
		Piece frontLeftPiece = board[currentX - 1][frontYPosition];

		if (frontPiece != null)
			list.add(frontPiece.pos);

		if (frontRightPiece != null && isEnemy(frontRightPiece))
			list.add(frontRightPiece.pos);
		if (frontLeftPiece != null && isEnemy(frontLeftPiece))
			list.add(frontRightPiece.pos);
	}

}
