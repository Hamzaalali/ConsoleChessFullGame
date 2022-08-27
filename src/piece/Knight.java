package piece;
import validation.chains.KnightChain;
public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
        pieceChain = new KnightChain();
        pieceChar='N';
    }
}
