package piece;

import validation.BaseChain;

public abstract class Piece{
    final Boolean isWhite;
    protected BaseChain pieceChain;
    protected char pieceChar;
    private int movesCount;
    private int lastMoveTurn;
    public Boolean isWhite() {
        return isWhite;
    }

    public Piece(Boolean isWhite){
        this.isWhite = isWhite;
        movesCount=0;
        lastMoveTurn=0;
    }
    public char getPieceChar() {
        return pieceChar;
    }

    public void setPieceChar(char pieceChar) {
        this.pieceChar = pieceChar;
    }
    public BaseChain getPieceChain() {
        return pieceChain;
    }
    public void moved(){
        movesCount++;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public boolean hasMoved() {
        return movesCount>0;
    }
    public int getLastMoveTurn() {
        return lastMoveTurn;
    }

    public void setLastMoveTurn(int lastMoveTurn) {
        this.lastMoveTurn = lastMoveTurn;
    }
}
