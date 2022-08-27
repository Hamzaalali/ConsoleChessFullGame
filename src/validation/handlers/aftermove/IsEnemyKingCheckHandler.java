package validation.handlers.aftermove;

import board.Board;
import board.Square;
import chess.GameVariables;
import board.Move;
import piece.King;
import validation.MoveHandler;

import java.util.List;

public class IsEnemyKingCheckHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Board board=gameVariables.getBoard();
        testIfMoveIsDone(gameVariables,move,false);//move the piece to ending square but not changing the turn
        Square enemyKingSquare=board.getPieceSquare(gameVariables.getEnemyPlayerKing());
        boolean isCheck=false;
        List<Square> occupiedSquares=board.getOccupiedSquares(gameVariables.isWhiteTurn());
        //here i will loop over all occupied squares and test if they can attack the enemy king
        for(Square square:occupiedSquares){
            try{
                Move testMove=new Move(square,enemyKingSquare);
                square.getPiece().getPieceChain().getBeforeMoveHandler().handleMove(gameVariables,testMove);
                square.getPiece().getPieceChain().getMoveHandler().handleMove(gameVariables,testMove);
                isCheck=true;//some piece can attack the enemy king
                break;
            }catch (Exception ignored){
            }
        }
        King enemyKing= (King) gameVariables.getEnemyPlayerKing();
        enemyKing.setInCheck(isCheck);//making the enemy king in check
        undoTestIfMoveIsDone(gameVariables,move,false);//return everything as it was before
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
    }
}