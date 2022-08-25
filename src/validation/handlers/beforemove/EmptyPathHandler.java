package validation.handlers.beforemove;

import board.Board;
import board.Square;
import chess.GameVariables;
import board.Move;
import exception.NotEmptyPathException;
import validation.MoveHandler;
import board.Direction;

public class EmptyPathHandler extends MoveHandler {

    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Square startingSquare = move.getStartingSquare();
        Square endingSquare = move.getEndingSquare();
        Board board=gameVariables.getBoard();
        boolean emptyPath=true;
        if(move.getDirection()== Direction.NORTH)
            emptyPath=emptyVerticalPath(board, startingSquare.getXPosition(), startingSquare.getYPosition()+1, endingSquare.getYPosition());
        if(move.getDirection()== Direction.SOUTH)
            emptyPath=emptyVerticalPath(board, startingSquare.getXPosition(), endingSquare.getYPosition()+1, startingSquare.getYPosition());
        if(move.getDirection()== Direction.EAST)
            emptyPath=emptyHorizontalPath(board,startingSquare.getXPosition()+1, endingSquare.getXPosition(), startingSquare.getYPosition());
        if(move.getDirection()== Direction.WEST)
            emptyPath=emptyHorizontalPath(board,endingSquare.getXPosition()+1, startingSquare.getXPosition(), startingSquare.getYPosition());
        if(move.getDirection()== Direction.NORTHEAST)
            emptyPath=emptyDiagonalPath(board,startingSquare.getXPosition()+1,startingSquare.getYPosition()+1, endingSquare.getYPosition());
        if(move.getDirection()== Direction.SOUTHWEST)
            emptyPath=emptyDiagonalPath(board,endingSquare.getXPosition()+1,endingSquare.getYPosition()+1, startingSquare.getYPosition());
        if(move.getDirection()== Direction.SOUTHEAST)
            emptyPath=emptyInverseDiagonalPath(board,endingSquare.getXPosition()-1,endingSquare.getYPosition()+1, startingSquare.getYPosition());
        if(move.getDirection()== Direction.NORTHWEST)
            emptyPath=emptyInverseDiagonalPath(board,startingSquare.getXPosition()-1,startingSquare.getYPosition()+1, endingSquare.getYPosition());
        if(move.getDirection()== Direction.UNKNOWN)
            return;
        if(!emptyPath){
            throw new NotEmptyPathException();
        }
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
    }
    boolean emptyHorizontalPath(Board board,int fromX,int toX,int y){
        boolean emptyPath=true;
        for (int xPosition=fromX;
             xPosition<toX;
             xPosition++){
            if(board.getPiece(xPosition,y)!=null)
                emptyPath=false;
        }
        return emptyPath;
    }
    boolean emptyVerticalPath(Board board,int x,int fromY,int toY){
        boolean emptyPath=true;
        for (int yPosition=fromY;
             yPosition<toY;
             yPosition++){
            if(board.getPiece(x,yPosition)!=null)
                emptyPath=false;
        }
        return emptyPath;
    }
    boolean emptyDiagonalPath(Board board,int x,int fromY,int toY){
        boolean emptyPath=true;
        for (int xPosition=x,yPosition=fromY;
             yPosition<toY;
             yPosition++,xPosition++){
            if(board.getPiece(xPosition,yPosition)!=null)
                emptyPath=false;
        }
        return emptyPath;
    }
    boolean emptyInverseDiagonalPath(Board board,int x,int fromY,int toY){
        boolean emptyPath=true;
        for (int xPosition=x,yPosition=fromY;
             yPosition<toY;
             yPosition++,xPosition--){
            if(board.getPiece(xPosition,yPosition)!=null)
                emptyPath=false;
        }
        return emptyPath;
    }
}