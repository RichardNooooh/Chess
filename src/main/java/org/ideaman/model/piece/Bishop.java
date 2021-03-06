package org.ideaman.model.piece;

import org.ideaman.utils.Position;

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
    public Piece copy()
    {
        return new Bishop(new Position(pos.getX(), pos.getY()), side);
    }

    @Override
    public List<Position> validMoveList(Piece[][] board)
    {
        LinkedList<Position> validPositionList = new LinkedList<Position>();
        checkDiagonal(board, validPositionList, pos, p -> p == null || isEnemy(p));
        return validPositionList;
    }
}
