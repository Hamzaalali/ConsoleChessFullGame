package piece;
import validation.chains.RockChain;
public class Rock extends Piece {
    public Rock(boolean isWhite) {
        super(isWhite);
        baseChain = new RockChain();
        pieceChar='R';
    }
}
