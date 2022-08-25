package validation.chains;

import validation.BaseChain;
import validation.handlers.pieces.normalmove.BishopMoveHandler;
import validation.handlers.pieces.normalmove.RockMoveHandler;

public class QueenChain extends BaseChain {
    public QueenChain(){
        moveHandler=new RockMoveHandler();
        moveHandler.setNext(new BishopMoveHandler());
    }
}
