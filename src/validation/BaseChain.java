package validation;


import chess.GameVariables;
import board.Move;
import validation.handlers.aftermove.*;
import validation.handlers.beforemove.EmptyPathHandler;
import validation.handlers.beforemove.IsMyKingInCheckHandler;
import validation.handlers.beforemove.IsMyPieceHandler;
import validation.handlers.beforemove.NotAttackingAllyHandler;

public abstract class BaseChain {


    protected MoveHandler moveHandler;
    private MoveHandler afterMoveHandler;

    protected MoveHandler specialMoveHandler;
    private MoveHandler beforeMoveHandler;

    public MoveHandler getBeforeMoveHandler() {
        return beforeMoveHandler;
    }

    protected BaseChain(){
        beforeMoveHandler =new IsMyPieceHandler();//will be checked before the move
        beforeMoveHandler.setNext(new NotAttackingAllyHandler()).setNext(new EmptyPathHandler()). setNext(new IsMyKingInCheckHandler());
        afterMoveHandler = new IsEnemyKingCheckHandler();//will be checked after the move
        afterMoveHandler.setNext(new IsCheckMateHandler()).setNext(new IsStalemateHandler()).setNext(new IsInsufficientMaterialHandler()).setNext(new CompleteTheMoveHandler());
    }
    public MoveHandler getMoveHandler() {
        return moveHandler;
    }
    public final void pieceChainHandler(GameVariables gameVariables, Move move) {
            beforeMoveHandler.handleMove(gameVariables,move);
            if(moveHandler!=null){
                moveHandler.handleMove(gameVariables,move);
            }
            if(specialMoveHandler!=null){
                specialMoveHandler.handleMove(gameVariables,move);
            }
            afterMoveHandler.handleMove(gameVariables,move);
    }
}
