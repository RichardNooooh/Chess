package piece;

public class Bishop extends Queen
{
    public Bishop(Position position)
    {
        type = PieceType.BISHOP;
        pos = position;
    }

    @Override
    public boolean canMove(Position newPosition, Piece[][] board)
    {
        canMove = false;
        previousCheckedPosition = newPosition;

        //TODO need check to make sure pos != newPosition
        if (pos.getX() == newPosition.getX() || pos.getY() == newPosition.getY())
            return canMove = checkLateral(newPosition, board);

        return false;
    }
}
