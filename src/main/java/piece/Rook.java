package piece;

import java.util.LinkedList;
import java.util.List;

public class Rook extends Queen
{
    public Rook(Position position, Side side)
    {
        type = PieceType.ROOK;
        pos = position;
        this.side = side;
    }

    @Override
    public List<Position> validMoveList(Piece[][] board)
    {
        LinkedList<Position> validPositionList = new LinkedList<Position>();
        checkDiagonal(board, validPositionList);
        return validPositionList;
    }
}
