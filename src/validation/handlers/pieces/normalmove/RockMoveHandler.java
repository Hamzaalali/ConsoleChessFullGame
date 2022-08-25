package validation.handlers.pieces.normalmove;

import chess.GameVariables;
import board.Move;
import exception.InvalidPieceMoveException;
import validation.MoveHandler;
import board.Direction;


public class RockMoveHandler extends MoveHandler {

    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        boolean isEast=move.getDirection() == Direction.EAST;
        boolean isSouth= move.getDirection() == Direction.SOUTH;
        boolean isNorth= move.getDirection() == Direction.NORTH;
        boolean isWest=  move.getDirection() == Direction.WEST;
        if(isEast || isSouth || isNorth || isWest)
            return;
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables, move);
        else
            throw new InvalidPieceMoveException();
    }
}
