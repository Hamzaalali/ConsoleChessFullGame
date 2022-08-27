package piece;
import validation.chains.BishopChain;
public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
        pieceChain = new BishopChain();
        pieceChar='B';
    }
}
