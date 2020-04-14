package piece;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece
{
    private final byte[] delX = new byte[]{1, 3, 3, 1, -1, -3, -3, -1};
    private final byte[] delY = new byte[]{3, 1, -1, -3, -3, -1, 1, 3};

    public Knight(Position position, Side side)
    {
        type = PieceType.KNIGHT;
        pos = position;
        this.side = side;
    }

    @Override
    public List<Position> validMoveList(Piece[][] board)
    {
        LinkedList<Position> validPositionList = new LinkedList<Position>();

        byte thisX = pos.getX();
        byte thisY = pos.getY();

        for (int i = 0; i < delX.length; i++)
        {
            byte currentX = (byte)(thisX + delX[i]);
            byte currentY = (byte)(thisY + delY[i]);

            if (Position.isOnBoard(currentX, currentY))
            {
                Piece currentPiece = board[currentX][currentY];
                if (currentPiece == null || isEnemy(currentPiece))
                    validPositionList.add(new Position(currentX, currentY));
            }
        }

        return validPositionList;
    }
}
