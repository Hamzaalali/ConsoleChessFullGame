package validation.chains;

import validation.BaseChain;
import validation.handlers.pieces.normalmove.BishopMoveHandler;

public class BishopChain extends BaseChain {
    public BishopChain(){
        moveHandler=new BishopMoveHandler();
    }
}
