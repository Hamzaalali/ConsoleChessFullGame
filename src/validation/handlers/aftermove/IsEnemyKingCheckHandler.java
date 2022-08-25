package validation.handlers.aftermove;

import board.Board;
import board.Square;
import chess.GameVariables;
import board.Move;
import validation.MoveHandler;

import java.util.List;

public class IsEnemyKingCheckHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Board board=gameVariables.getBoard();
        testIfMoveIsDone(gameVariables,move,false);
        Square enemyKingSquare=board.getPieceSquare(gameVariables.getEnemyPlayerKing());
        boolean isCheck=false;
        List<Square> occupiedSquares=board.getOccupiedSquares(gameVariables.isWhiteTurn());
        for(Square square:occupiedSquares){
            try{
                Move testMove=new Move(square,enemyKingSquare);
                square.getPiece().getBaseChain().getBeforeMoveHandler().handleMove(gameVariables,testMove);
                square.getPiece().getBaseChain().getMoveHandler().handleMove(gameVariables,testMove);
                isCheck=true;
                break;
            }catch (Exception ignored){
            }
        }
        gameVariables.getEnemyPlayer().setInCheck(isCheck);
        undoTestIfMoveIsDone(gameVariables,move,false);

        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);

    }
}