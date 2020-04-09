public class Queen extends Piece
{
    
    public Queen(Position position)
    {
        type = PieceType.PAWN;
        pos = position;
    }

    @Override
    public boolean canMove(Position newPosition, Piece[][] board)
    {
        return false;
    }

    protected boolean checkLateral(Position newPosition, Piece[][] board)
    {
        return false;
    }

    protected boolean checkDiagonal(Position newPosition, Piece[][] board)
    {
        return false;
    }

}
//TODO Rook and Bishop will extend Queen since they're limited versions of the Queen