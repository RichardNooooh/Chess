package piece;

import java.util.function.Function;
import java.util.function.Predicate;

public class Queen extends Piece
{

    public Queen(Position position)
    {
        type = PieceType.QUEEN;
        pos = position;
    }

    protected Queen(){}

    @Override
    public boolean canMove(Position newPosition, Piece[][] board)
    {
        previousCheckedPosition = newPosition;

        //TODO need check to make sure pos != newPosition
        byte thisX = pos.getX();
        byte thisY = pos.getY();
        byte newX = newPosition.getX();
        byte newY = newPosition.getY();

        if (thisX == newX || thisY == newY)
            return canMove = checkLateral(newPosition, board);
        else
            return canMove = checkDiagonal(newPosition, board);
    }

    protected boolean checkLateral(Position newPos, Piece[][] board)
    {
        int xDiff = newPos.getX() - pos.getX();
        int yDiff = newPos.getY() - pos.getY();

        Piece blockingPiece = null;
        if (xDiff == 0)
        {
            Predicate<Position> endPosition = s -> s.getY() == newPos.getY();
            Function<Integer, Integer> horizontalConstant = x -> x;

            if (yDiff > 0)
                blockingPiece = loop(endPosition, horizontalConstant, y -> y + 1, board);
            else if (yDiff < 0)
                blockingPiece = loop(endPosition, horizontalConstant, y -> y - 1, board);
        }
        else
        {
            Predicate<Position> endPosition = s -> s.getX() == newPos.getX();
            Function<Integer, Integer> verticalConstant = y -> y;

            if (xDiff > 0)
                blockingPiece = loop(endPosition, x -> x + 1, verticalConstant, board);
            else if (xDiff < 0)
                blockingPiece = loop(endPosition, x -> x - 1, verticalConstant, board);
        }

        if (blockingPiece != null)
            return isEnemy(blockingPiece);

        return false;
    }

    protected boolean checkDiagonal(Position newPos, Piece[][] board)
    {
        int xDiff = newPos.getX() - pos.getX();
        int yDiff = newPos.getY() - pos.getY();

        Piece blockingPiece = null;
        Predicate<Position> endPosition = s -> (s.getX() == newPos.getX()) && (s.getY() == newPos.getY());
        if (xDiff > 0)
        {
            Function<Integer, Integer> horizontalChange = x -> x + 1;
            if (yDiff > 0)
                blockingPiece = loop(endPosition, horizontalChange, y -> y + 1, board);
            else
                blockingPiece = loop(endPosition, horizontalChange, y -> y - 1, board);
        }
        else
        {
            Function<Integer, Integer> horizontalChange = x -> x - 1;
            if (yDiff > 0)
                blockingPiece = loop(endPosition, horizontalChange, y -> y + 1, board);
            else
                blockingPiece = loop(endPosition, horizontalChange, y -> y - 1, board);
        }

        if (blockingPiece != null)
            return isEnemy(blockingPiece);

        return false;
    }

    private Piece loop(Predicate<Position> endPosition,
                       Function<Integer, Integer> xFunction,
                       Function<Integer, Integer> yFunction,
                       Piece[][] board)
    {
        Position currentPos = pos;
        while (!endPosition.test(currentPos))
        {
            currentPos = nextTile(currentPos, xFunction, yFunction);
            Piece currentPiece = board[currentPos.getX()][currentPos.getY()];
            if (currentPiece != null)
                return currentPiece;
        }
        return null;
    }

    private Position nextTile(Position current,
                              Function<Integer, Integer> xFunction,
                              Function<Integer, Integer> yFunction)
    {
        //TODO fix this jank
        int newX = xFunction.apply((int) current.getX());
        int newY = yFunction.apply((int) current.getY());
        return new Position((byte)newX, (byte)newY);
    }


}
//TODO Rook and Bishop will extend Queen since they're limited versions of the Queen