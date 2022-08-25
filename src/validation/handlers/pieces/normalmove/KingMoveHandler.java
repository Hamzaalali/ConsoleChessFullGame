package validation.handlers.pieces.normalmove;

import chess.GameVariables;
import board.Move;
import exception.InvalidPieceMoveException;
import validation.MoveHandler;
import board.Direction;

public class KingMoveHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        if(move.getSteps()==1 && move.getDirection()!= Direction.UNKNOWN){
            return;
        }
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables, move);
        else
            throw new InvalidPieceMoveException();
    }
}
