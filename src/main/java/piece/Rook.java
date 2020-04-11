package piece;

public class Rook extends Queen
{
    public Rook(Position position, Side side)
    {
        type = PieceType.ROOK;
        pos = position;
        this.side = side;
    }

    @Override
    public boolean canMove(Position newPosition, Piece[][] board)
    {
        canMove = false;
        previousCheckedPosition = newPosition;
        //TODO need check to make sure pos != newPosition
        if (pos.getX() != newPosition.getX() && pos.getY() != newPosition.getY())
            return canMove = checkDiagonal(newPosition, board);

        return false;
    }
}
