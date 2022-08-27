package piece;

import validation.chains.KingChain;

public class King extends Piece {
    boolean isInCheck;
    public King(boolean isWhite) {
        super(isWhite);
        pieceChain = new KingChain();
        pieceChar='K';
        isInCheck=false;
    }
    public boolean isInCheck() {

        return isInCheck;
    }
    public void setInCheck(boolean inCheck) {
        isInCheck = inCheck;
        if(isInCheck){
            if(isWhite)System.out.println("White King In Check");
            else System.out.println("Black King In Check");
        }
    }
}
