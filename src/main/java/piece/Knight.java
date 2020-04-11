package piece;

public class Knight extends Piece
{
    //TODO refactor this into a singleton or something
    private final Position[] delPositions = {new Position((byte) 1, (byte) 3),
                                             new Position((byte) 3, (byte) 1),
                                             new Position((byte) 3, (byte) -1),
                                             new Position((byte) 1, (byte) -3),
                                             new Position((byte) -1, (byte) -3),
                                             new Position((byte) -3, (byte) -1),
                                             new Position((byte) -3, (byte) 1),
                                             new Position((byte) -1, (byte) 3)};

    public Knight(Position position)
    {
        type = PieceType.KNIGHT;
        pos = position;
    }

    @Override
    public boolean canMove(Position newPosition, Piece[][] board)
    {
        byte diffX = (byte)(newPosition.getX() - pos.getX());
        byte diffY = (byte)(newPosition.getY() - pos.getY());
        Position difPosition = new Position(diffX, diffY);
        for (Position validPos : delPositions)
        {
            if (difPosition.equals(validPos))
                return true;
        }

        return false;
    }
}
