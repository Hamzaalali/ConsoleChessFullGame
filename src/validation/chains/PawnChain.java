package validation.chains;

import validation.handlers.pieces.special.EnPassantMoveHandler;
import validation.BaseChain;
import validation.handlers.pieces.normalmove.PawnMoveHandler;
import validation.handlers.pieces.special.PawnPromotionHandler;

public class PawnChain extends BaseChain {
    public PawnChain() {
        moveHandler=new PawnMoveHandler();
        moveHandler.setNext(new EnPassantMoveHandler());
        specialMoveHandler=new PawnPromotionHandler();
    }
}
