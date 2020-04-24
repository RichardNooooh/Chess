package org.ideaman.model.piece;

import org.ideaman.utils.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class Queen extends Piece
{

    public Queen(Position position, Side side)
    {
        type = PieceType.QUEEN;
        pos = position;
        this.side = side;
    }

    protected Queen(){}

    @Override
    public List<Position> validMoveList(Piece[][] board)
    {
        LinkedList<Position> validPositions = new LinkedList<Position>();

        checkLateral(board, validPositions, pos, p -> p == null || isEnemy(p));
        checkDiagonal(board, validPositions, pos, p -> p == null || isEnemy(p));

        return validPositions;
    }

    @Override
    public Piece copy()
    {
        return new Queen(new Position(pos.getX(), pos.getY()), side);
    }

    protected static void checkLateral(Piece[][] board, LinkedList<Position> list, Position position, Predicate<Piece> selectionPredicate)
    {
        Function<Integer, Integer> constantDel = s -> s;
        Function<Integer, Integer> posDel = s -> s + 1;
        Function<Integer, Integer> negDel = s -> s - 1;

        loop(constantDel, posDel, selectionPredicate, board, list, position);
        loop(constantDel, negDel, selectionPredicate, board, list, position);
        loop(posDel, constantDel, selectionPredicate, board, list, position);
        loop(negDel, constantDel, selectionPredicate, board, list, position);
    }

    protected static void checkDiagonal(Piece[][] board, LinkedList<Position> list, Position position, Predicate<Piece> selectionPredicate)
    {
        Function<Integer, Integer> posDel = s -> s + 1;
        Function<Integer, Integer> negDel = s -> s - 1;

        loop(posDel, posDel, selectionPredicate, board, list, position);
        loop(posDel, negDel, selectionPredicate, board, list, position);
        loop(negDel, posDel, selectionPredicate, board, list, position);
        loop(negDel, negDel, selectionPredicate, board, list, position);
    }

    private static void loop(Function<Integer, Integer> xFunction,
                        Function<Integer, Integer> yFunction,
                        Predicate<Piece> selectionPredicate,
                        Piece[][] board,
                        List<Position> list,
                        Position position)
    {
        Position currentPos = position;
        Piece currentPiece = null;
        while (currentPiece == null)
        {
            currentPos = nextTile(currentPos, xFunction, yFunction);
            if (currentPos != null)
            {
                currentPiece = board[currentPos.getX()][currentPos.getY()];

                if (selectionPredicate.test(currentPiece))
                    list.add(currentPos);
            }
            else
                break;
        }
    }

    private static Position nextTile(Position current,
                              Function<Integer, Integer> xFunction,
                              Function<Integer, Integer> yFunction)
    {
        //TODO fix this jank
        int newX = xFunction.apply((int) current.getX());
        int newY = yFunction.apply((int) current.getY());
        if (Position.isOnBoard(newX, newY))
            return new Position((byte)newX, (byte)newY);
        else
            return null;
    }


}
//TODO Rook and Bishop will extend Queen since they're limited versions of the Queen