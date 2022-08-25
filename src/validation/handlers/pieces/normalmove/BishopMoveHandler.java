package validation.handlers.pieces.normalmove;

import chess.GameVariables;
import board.Move;
import exception.InvalidPieceMoveException;
import validation.MoveHandler;
import board.Direction;

public class BishopMoveHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        boolean isNorthEast=move.getDirection()== Direction.NORTHEAST;
        boolean isNorthWest=move.getDirection()== Direction.NORTHWEST;
        boolean isSouthWest=move.getDirection()== Direction.SOUTHWEST;
        boolean isSouthEast=move.getDirection()== Direction.SOUTHEAST;
        if(isNorthEast || isNorthWest|| isSouthEast||isSouthWest)
            return;

        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables, move);
        else
            throw new InvalidPieceMoveException();
    }
}
