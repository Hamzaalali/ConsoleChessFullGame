package piece;
import validation.chains.QueenChain;
public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
        baseChain =new QueenChain();
        pieceChar='Q';
    }
}
