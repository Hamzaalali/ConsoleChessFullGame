package validation.handlers.pieces.normalmove;

import board.Square;
import chess.GameVariables;
import board.Move;
import exception.InvalidPieceMoveException;
import validation.MoveHandler;
import board.Direction;

import static java.lang.Math.abs;

public class PawnMoveHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        boolean validMove;
        Direction normalMoveDirection;
        Direction attackRightDirection;
        Direction attackLeftDirection;
        if(gameVariables.isWhiteTurn()) {
            normalMoveDirection=Direction.NORTH;
            attackRightDirection=Direction.NORTHEAST;
            attackLeftDirection=Direction.NORTHWEST;
        }
        else{
            normalMoveDirection=Direction.SOUTH;
            attackRightDirection=Direction.SOUTHEAST;
            attackLeftDirection=Direction.SOUTHWEST;
        }
        validMove=isPawnMove(move,normalMoveDirection,attackRightDirection,attackLeftDirection);
        if(validMove)
            return;
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables, move);
        else
            throw new InvalidPieceMoveException();
    }
    boolean isPawnMove(Move move,Direction normalMoveDirection,Direction attackRightDirection,Direction attackLeftDirection){
        //normal move
        if(move.getDirection()== normalMoveDirection && isNormalMove(move)){
            return true;
        }
        //attack move
        if(move.getDirection()== attackRightDirection || move.getDirection()== attackLeftDirection){
           return isAttackMove(move);
        }
        return false;
    }
    boolean isNormalMove(Move move){
        Square startingSquare=move.getStartingSquare();
        Square endingSquare= move.getEndingSquare();
        boolean canMoveTwoSteps=move.getSteps()==2&& !startingSquare.getPiece().hasMoved();
        return (move.getSteps() == 1 || canMoveTwoSteps) && endingSquare.getPiece() == null;
    }
    boolean isAttackMove(Move move){
        Square endingSquare= move.getEndingSquare();
        return move.getSteps() == 1 && endingSquare.getPiece() != null;
    }
}
