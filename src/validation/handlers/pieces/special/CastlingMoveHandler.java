package validation.handlers.pieces.special;

import board.Board;
import board.Square;
import chess.GameVariables;
import board.Move;
import validation.MoveHandler;
import piece.Piece;
import board.Direction;

public class CastlingMoveHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Square startingSquare=move.getStartingSquare();
        Square endingSquare= move.getEndingSquare();
        Board board=gameVariables.getBoard();
        boolean canCastle=true;
        if(startingSquare.getPiece().hasMoved()){
            canCastle=false;
        }
        if(gameVariables.getCurrentPlayer().isInCheck()){
            canCastle=false;
        }
        int rockInitialXPosition =-1;
        int rockFinalXPPosition =-1;
        boolean canCastleToWest=false;
        boolean canCastleToEast=false;
        if(move.getDirection()== Direction.EAST && move.getSteps()==2 && canCastle){
            rockInitialXPosition=8;
            rockFinalXPPosition=endingSquare.getXPosition()-1;
            canCastleToEast=true;
        }
        if(move.getDirection()== Direction.WEST && move.getSteps()==2 && canCastle){
            rockInitialXPosition=1;
            rockFinalXPPosition=endingSquare.getXPosition()+1;
            canCastleToWest=true;
        }
        boolean isCastle=false;
        if(canCastleToWest||canCastleToEast)
            isCastle=isCastle(gameVariables,move,rockInitialXPosition);
        if(isCastle){
            Piece rock= board.removePiece(rockInitialXPosition,endingSquare.getYPosition());
            board.setPiece(rockFinalXPPosition,endingSquare.getYPosition(),rock);
            return;
        }
        checkForNextHandler(gameVariables,move);
    }
    boolean isCastle(GameVariables gameVariables,Move move,int rockXPosition){
        Square endingSquare= move.getEndingSquare();
        Board board=gameVariables.getBoard();
        Piece rock=board.getPiece(rockXPosition,endingSquare.getYPosition());
        if(rock==null){
            return false;
        }
        if(rock.hasMoved()){
            return false;
        }
        return endingSquare.getPiece() == null || board.getSquare(endingSquare.getXPosition(), endingSquare.getYPosition()).getPiece() == null;
    }
}
