package piece;

import java.util.LinkedList;
import java.util.List;

public class King extends Piece
{
    private final byte[] delX = new byte[]{0, 1, 1, 1, 0, -1, -1, -1};
    private final byte[] delY = new byte[]{1, 1, 0, -1, -1, -1, 0, 1};

    public King(Position position, Side side)
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
            //TODO need to check if a given position would put it in check
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


//    private boolean isInCheck(Piece[][] board, byte xPos, byte yPos)
//    {
//
//        return false;
//    }

}
