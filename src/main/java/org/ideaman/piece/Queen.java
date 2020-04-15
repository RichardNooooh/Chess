package org.ideaman.piece;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

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

        checkLateral(board, validPositions);
        checkDiagonal(board, validPositions);

        return validPositions;
    }

    protected void checkLateral(Piece[][] board, LinkedList<Position> list)
    {
        Function<Integer, Integer> constantDel = s -> s;
        Function<Integer, Integer> posDel = s -> s + 1;
        Function<Integer, Integer> negDel = s -> s - 1;

        loop(constantDel, posDel, board, list);
        loop(constantDel, negDel, board, list);
        loop(posDel, constantDel, board, list);
        loop(negDel, constantDel, board, list);
    }

    protected void checkDiagonal(Piece[][] board, LinkedList<Position> list)
    {
        Function<Integer, Integer> posDel = s -> s + 1;
        Function<Integer, Integer> negDel = s -> s - 1;

        loop(posDel, posDel, board, list);
        loop(posDel, negDel, board, list);
        loop(negDel, posDel, board, list);
        loop(negDel, negDel, board, list);
    }

    private void loop(Function<Integer, Integer> xFunction,
                      Function<Integer, Integer> yFunction,
                      Piece[][] board,
                      List<Position> list)
    {
        Position currentPos = pos;
        Piece currentPiece = null;
        while (currentPiece == null)
        {
            currentPos = nextTile(currentPos, xFunction, yFunction);
            if (currentPos != null)
            {
                currentPiece = board[currentPos.getX()][currentPos.getY()];
                if (currentPiece == null || isEnemy(currentPiece)) //TODO test this. pretty sure the boolean short circuit makes this safe.
                    list.add(currentPos);
            }
            else
                break;
        }
    }

    private Position nextTile(Position current,
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