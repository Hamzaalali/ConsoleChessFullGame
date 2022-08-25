package board;

import piece.Piece;

public class Square {
    final int xPosition;
    final int yPosition;

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    Piece piece;
    Square(int xPosition,int yPosition){
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        piece=null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }


}
