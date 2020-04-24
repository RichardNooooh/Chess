package org.ideaman.model.piece;

import org.ideaman.utils.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class King extends Piece
{
    private final byte[] delX = new byte[]{0, 1, 1, 1, 0, -1, -1, -1};
    private final byte[] delY = new byte[]{1, 1, 0, -1, -1, -1, 0, 1};

    public King(Position position, Side side)
    {
        type = PieceType.KING;
        pos = position;
        this.side = side;
    }

    @Override
    public Piece copy()
    {
        return new King(new Position(pos.getX(), pos.getY()), side);
    }

    @Override
    public List<Position> validMoveList(Piece[][] board)
    {
        LinkedList<Position> validPositionList = new LinkedList<Position>();

        byte thisX = pos.getX();
        byte thisY = pos.getY();

        for (int i = 0; i < delX.length; i++)
        {
            //TODO need to check if a given position would put it in check
            byte currentX = (byte)(thisX + delX[i]);
            byte currentY = (byte)(thisY + delY[i]);

            if (Position.isOnBoard(currentX, currentY))
            {
                Piece currentPiece = board[currentX][currentY];
                if (currentPiece == null || (isEnemy(currentPiece) && notInCheck(board, currentX, currentY)))
                    validPositionList.add(new Position(currentX, currentY));
            }
        }

        return validPositionList;
    }


    private boolean notInCheck(Piece[][] board, int x, int y)
    {
        LinkedList<Position> seenEnemies = new LinkedList<Position>();
        Position checkPosition = new Position(x, y);
        Predicate<Piece> potentialAttacker = p -> p != null && isEnemy(p);
        Queen.checkDiagonal(board, seenEnemies, checkPosition, potentialAttacker);

        Queen.checkLateral(board, seenEnemies, checkPosition, potentialAttacker);
        Knight.getPositions(seenEnemies, board, potentialAttacker, checkPosition);

        for (Position potentialEnemy : seenEnemies)
        {
            byte enemyX = potentialEnemy.getX();
            byte enemyY = potentialEnemy.getY();
            Piece enemy = board[enemyX][enemyY];
            List<Position> enemyMovePositions = enemy.validMoveList(board);
            if (enemyMovePositions.contains(pos))
                return false;
        }

        return true;
    }

}
