package org.ideaman.model.piece;

import org.ideaman.utils.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Knight extends Piece
{
    private static final byte[] delX = new byte[]{1, 2, 2, 1, -1, -2, -2, -1};
    private static final byte[] delY = new byte[]{2, 1, -1, -2, -2, -1, 1, 2};

    public Knight(Position position, Side side)
    {
        type = PieceType.KNIGHT;
        pos = position;
        this.side = side;
    }

    @Override
    public Piece copy()
    {
        return new Knight(new Position(pos.getX(), pos.getY()), side);
    }

    @Override
    public List<Position> validMoveList(Piece[][] board)
    {
        LinkedList<Position> validPositionList = new LinkedList<Position>();
        Predicate<Piece> validMovement = p -> p == null || isEnemy(p);

        getPositions(validPositionList, board, validMovement, pos);

        return validPositionList;
    }

    protected static void getPositions(List<Position> list, Piece[][] board, Predicate<Piece> selectionPredicate, Position position)
    {
        byte thisX = position.getX();
        byte thisY = position.getY();

        for (int i = 0; i < delX.length; i++)
        {
            byte currentX = (byte)(thisX + delX[i]);
            byte currentY = (byte)(thisY + delY[i]);

            if (Position.isOnBoard(currentX, currentY))
            {
                Piece currentPiece = board[currentX][currentY];
                if (selectionPredicate.test(currentPiece))
                    list.add(new Position(currentX, currentY));
            }
        }
    }
}
