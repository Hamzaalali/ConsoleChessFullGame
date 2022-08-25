package validation.chains;

import validation.BaseChain;
import validation.handlers.pieces.normalmove.RockMoveHandler;

public class RockChain extends BaseChain {
    public RockChain(){
        moveHandler= new RockMoveHandler();
    }
}
