package board;

import chess.GameVariables;
import piece.Piece;

public class Move {
    Square startingSquare;

    public void setStartingSquarePiece(Piece startingSquarePiece) {
        this.startingSquarePiece = startingSquarePiece;
    }

    Piece startingSquarePiece;
    Piece endingSquarePiece;

    public Square getStartingSquare() {
        return startingSquare;
    }

    public Square getEndingSquare() {
        return endingSquare;
    }

    Square endingSquare;

    public Piece getStartingSquarePiece() {
        return startingSquarePiece;
    }

    public Piece getEndingSquarePiece() {
        return endingSquarePiece;
    }

    Direction direction;
    int steps;

    public Move(GameVariables gameVariables, String startingSquareString, String endingSquareString){
        int startingSquareXPosition=startingSquareString.charAt(0)-'a'+1;
        int startingSquareYPosition=startingSquareString.charAt(1)-'0';
        int endingSquareXPosition=endingSquareString.charAt(0)-'a'+1;
        int endingSquareYPosition=endingSquareString.charAt(1)-'0';
        startingSquare=gameVariables.getBoard().getSquare(startingSquareXPosition,startingSquareYPosition);
        endingSquare=gameVariables.getBoard().getSquare(endingSquareXPosition,endingSquareYPosition);
        startingSquarePiece=startingSquare.getPiece();
        endingSquarePiece=endingSquare.getPiece();
        findDirection();
        findSteps();
    }
    public Move( Square startingSquare, Square endingSquare){
        this.startingSquare=startingSquare;
        this.endingSquare=endingSquare;
        startingSquarePiece=startingSquare.getPiece();
        endingSquarePiece=endingSquare.getPiece();
        findDirection();
        findSteps();
    }
    public Direction getDirection(){
        return  direction;
    }
    private void findDirection(){
        boolean isVertical=startingSquare.getXPosition()== endingSquare.getXPosition();
        boolean isHorizontal=startingSquare.getYPosition()== endingSquare.getYPosition();
        boolean isDiagonal=(((float)startingSquare.getXPosition()- endingSquare.getXPosition()) / (startingSquare.getYPosition()- endingSquare.getYPosition()))==1.0;
        boolean isInverseDiagonal=(((float)startingSquare.getXPosition()- endingSquare.getXPosition()) / (startingSquare.getYPosition()- endingSquare.getYPosition()))==-1.0;
        if(isVertical){
            setVerticalDirection();
            return;
        }
        if (isHorizontal) {
            setHorizontalDirection();
            return;
        }
        if (isDiagonal) {
            setDiagonalDirection();
            return;
        }
        if (isInverseDiagonal) {
           setInverseDiagonalDirection();
           return;
        }
        direction=Direction.UNKNOWN;
    }
    private void findSteps(){
        if(direction==Direction.EAST || direction==Direction.WEST){
            steps = Math.abs(startingSquare.getXPosition()- endingSquare.getXPosition());
        }else {
            steps = Math.abs(startingSquare.getYPosition()- endingSquare.getYPosition());
        }
    }
    public int getSteps(){
        return steps;
    }
    void setHorizontalDirection(){
        if(startingSquare.getXPosition()>endingSquare.getXPosition())
            direction=Direction.WEST;
        else
            direction=Direction.EAST;
    }
    void setVerticalDirection(){
        if(startingSquare.getYPosition()>endingSquare.getYPosition())
            direction=Direction.SOUTH;
        else
            direction=Direction.NORTH;
    }
    void setDiagonalDirection(){
        if(startingSquare.getXPosition()>endingSquare.getXPosition())
            direction=Direction.SOUTHWEST;
        else
            direction=Direction.NORTHEAST;
    }
    void setInverseDiagonalDirection(){
        if(startingSquare.getXPosition()>endingSquare.getXPosition())
            direction=Direction.NORTHWEST;
        else
            direction=Direction.SOUTHEAST;
    }
}
