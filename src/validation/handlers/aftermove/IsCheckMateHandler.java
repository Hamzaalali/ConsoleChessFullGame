package validation.handlers.aftermove;

import board.Board;
import chess.GameStatus;
import chess.GameVariables;
import board.Move;
import piece.King;
import validation.MoveHandler;


public class IsCheckMateHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        testIfMoveIsDone(gameVariables,move,true);
        Board board=gameVariables.getBoard();
        boolean canBeSaved;
        boolean checkMate=false;
        King myKing=(King) gameVariables.getCurrentPlayerKing();
        if(myKing.isInCheck()){
            canBeSaved = canMove(gameVariables, board);//if the enemy king is in check and can move then he will be saved
            if(!canBeSaved)
                checkMate=true;// the enemy king will not be saved then it's checkmate
        }
        undoTestIfMoveIsDone(gameVariables,move,true);
        if(checkMate){
            gameVariables.setGameStatus(GameStatus.CHECKMATE);
        }
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
    }
}
