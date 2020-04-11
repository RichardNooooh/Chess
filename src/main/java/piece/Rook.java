package piece;

public class Rook extends Queen
{
    public Rook(Position position)
    {
        type = PieceType.ROOK;
        pos = position;
    }

    @Override
    public boolean canMove(Position newPosition, Piece[][] board)
    {
        //TODO need check to make sure pos != newPosition
        if (pos.getX() != newPosition.getX() && pos.getY() != newPosition.getY())
            return checkDiagonal(newPosition, board);

        return false;
    }
}
