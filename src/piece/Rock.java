package piece;
import validation.chains.RockChain;
public class Rock extends Piece {
    public Rock(boolean isWhite) {
        super(isWhite);
        pieceChain = new RockChain();
        pieceChar='R';
    }
}
