package piece;

import validation.chains.KingChain;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
        baseChain = new KingChain();
        pieceChar='K';
    }
}
