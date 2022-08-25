package validation.handlers.aftermove;

import board.Board;
import chess.GameStatus;
import chess.GameVariables;
import board.Move;
import validation.MoveHandler;


public class IsStalemateHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        testIfMoveIsDone(gameVariables,move,true);
        Board board=gameVariables.getBoard();
        boolean hasMoves;
        if(!gameVariables.getCurrentPlayer().isInCheck()){
            hasMoves = canMove(gameVariables, board);
        }else{
            hasMoves=true;
        }
        if(!hasMoves){
            System.out.println("STALEMATE !");
            System.out.println("DRAW !");
            gameVariables.setGameStatus(GameStatus.OVER);
        }
        undoTestIfMoveIsDone(gameVariables,move,true);
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
    }
}