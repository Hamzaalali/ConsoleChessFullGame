package piece;
import validation.chains.KnightChain;
public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
        baseChain = new KnightChain();
        pieceChar='N';
    }
}
