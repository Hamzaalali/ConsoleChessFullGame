package validation.handlers.aftermove;

import board.Board;
import chess.GameStatus;
import chess.GameVariables;
import board.Move;
import piece.King;
import validation.MoveHandler;


public class IsStalemateHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        testIfMoveIsDone(gameVariables,move,true);// will change turn to be next turn and move the piece to ending square
        Board board=gameVariables.getBoard();
        boolean hasMoves;
        King myKing=(King) gameVariables.getCurrentPlayerKing();
        if(!myKing.isInCheck()){
            hasMoves = canMove(gameVariables, board);
        }else{
            hasMoves=true;
        }
        if(!hasMoves){
            gameVariables.setGameStatus(GameStatus.STALEMATE);//if the enemy king is not in check and the player has no moves then it's stalemate
        }
        undoTestIfMoveIsDone(gameVariables,move,true);
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
    }
}