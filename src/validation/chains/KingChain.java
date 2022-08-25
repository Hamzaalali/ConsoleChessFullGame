package validation.chains;

import validation.BaseChain;
import validation.handlers.pieces.normalmove.KingMoveHandler;
import validation.handlers.pieces.special.CastlingMoveHandler;

public class KingChain extends BaseChain {
    public KingChain(){
        moveHandler=new KingMoveHandler();
        moveHandler.setNext(new CastlingMoveHandler());
    }
}
