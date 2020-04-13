package piece;

import java.util.LinkedList;
import java.util.List;

public class Bishop extends Queen
{
    public Bishop(Position position, Side side)
    {
        type = PieceType.BISHOP;
        pos = position;
        this.side = side;
    }

    @Override
    public List<Position> validMoveList(Piece[][] board)
    {
        LinkedList<Position> validPositionList = new LinkedList<Position>();
        checkLateral(board, validPositionList);
        return validPositionList;
    }
}
