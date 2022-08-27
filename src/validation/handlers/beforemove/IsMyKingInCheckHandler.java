
package validation.handlers.beforemove;

import board.Board;

import board.Square;
import chess.GameVariables;
import board.Move;
import validation.exception.MoveWIllCheckMyKingException;
import validation.MoveHandler;

import java.util.List;

public class  IsMyKingInCheckHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Board board=gameVariables.getBoard();
        testIfMoveIsDone(gameVariables,move,true);
        Square myKingSquare=board.getPieceSquare(gameVariables.getEnemyPlayerKing());
        boolean isCheck=false;
        List<Square> occupiedSquares=board.getOccupiedSquares(gameVariables.isWhiteTurn());
        for(Square square:occupiedSquares){
            try{
                Move testMove=new Move(square,myKingSquare);
                square.getPiece().getPieceChain().getBeforeMoveHandler().handleMove(gameVariables,testMove);
                square.getPiece().getPieceChain().getMoveHandler().handleMove(gameVariables,testMove);
                isCheck=true;// there is an enemy piece that can attack my king if i made the move
            }catch (Exception ignored){
            }
        }
        undoTestIfMoveIsDone(gameVariables,move,true);

        if(isCheck){
            throw new MoveWIllCheckMyKingException();
        }
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables, move);
    }
}
