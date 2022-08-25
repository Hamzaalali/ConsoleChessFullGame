package validation.handlers.pieces.normalmove;

import board.Square;
import chess.GameVariables;
import board.Move;
import validation.MoveHandler;
import board.Direction;

public class KnightMoveHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Square startingSquare=move.getStartingSquare();
        Square endingSquare= move.getEndingSquare();
        if(move.getDirection()== Direction.UNKNOWN){
            int changeInX=Math.abs(startingSquare.getXPosition()- endingSquare.getXPosition());
            int changeInY=Math.abs(startingSquare.getYPosition()- endingSquare.getYPosition());
            if(changeInX+changeInY==3 && changeInX!=0 && changeInY!=0){
                return;
            }
        }
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
        else
            throw new IllegalArgumentException();
    }
}
