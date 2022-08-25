package validation.chains;

import validation.BaseChain;
import validation.handlers.pieces.normalmove.KnightMoveHandler;

public class KnightChain extends BaseChain {
    public KnightChain(){
        moveHandler=new KnightMoveHandler();
    }
}
