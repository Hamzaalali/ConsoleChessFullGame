package validation.handlers.aftermove;

import board.Board;
import chess.GameStatus;
import chess.GameVariables;
import board.Move;
import validation.MoveHandler;


public class IsCheckMateHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        testIfMoveIsDone(gameVariables,move,true);
        Board board=gameVariables.getBoard();
        boolean canBeSaved;
        boolean checkMate=false;
        if(gameVariables.getCurrentPlayer().isInCheck()){
            canBeSaved = canMove(gameVariables, board);
            if(!canBeSaved)
                checkMate=true;
        }
        undoTestIfMoveIsDone(gameVariables,move,true);
        if(checkMate){
            System.out.println("CHECKMATE !");
            System.out.println(gameVariables.getCurrentPlayer().getName()+" WON!");
            gameVariables.setGameStatus(GameStatus.OVER);
        }
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
    }
}
