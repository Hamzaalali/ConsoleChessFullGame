package piece;
import validation.chains.PawnChain;
public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
        pieceChain =new PawnChain();
        pieceChar='P';
    }

}
