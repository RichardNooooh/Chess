package piece;

public class King extends Piece
{
    private final Position[] delPositions = {new Position((byte) 0, (byte) 1),
                                             new Position((byte) 1, (byte) 1),
                                             new Position((byte) 1, (byte) 0),
                                             new Position((byte) 1, (byte) -1),
                                             new Position((byte) 0, (byte) -1),
                                             new Position((byte) -1, (byte) -1),
                                             new Position((byte) -1, (byte) 0),
                                             new Position((byte) -1, (byte) 1)};
    public King(Position position)
    {
        type = PieceType.KNIGHT;
        pos = position;
    }

    @Override
    public boolean canMove(Position newPosition, Piece[][] board)
    {
        previousCheckedPosition = newPosition;
        //check if piece is in stalemate
        byte diffX = (byte)(newPosition.getX() - pos.getX());
        byte diffY = (byte)(newPosition.getY() - pos.getY());
        Position difPosition = new Position(diffX, diffY);
        for (Position validPos : delPositions)
        {
            if (difPosition.equals(validPos) && isEnemy(board[newPosition.getX()][newPosition.getY()]))
                return canMove = true;
        }

        return false;
    }
}
