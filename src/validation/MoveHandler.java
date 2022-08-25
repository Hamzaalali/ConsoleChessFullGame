package validation;

import board.Board;
import board.Square;
import chess.GameVariables;
import board.Move;
import exception.InvalidPieceMoveException;
import validation.handlers.beforemove.IsMyKingInCheckHandler;

import java.util.List;

public abstract class MoveHandler {

    protected MoveHandler nextHandler = null;

    public MoveHandler setNext(MoveHandler nextHandler){
        if(nextHandler == null)
            throw new IllegalArgumentException();
        MoveHandler temp=this;
        while(temp.nextHandler != null){
            temp = temp.nextHandler;
        }
        temp.nextHandler = nextHandler;
        return this.nextHandler;
    }
    public abstract void handleMove(GameVariables gameVariables, Move move);

    protected void checkForNextHandler(GameVariables gameVariables,Move move){
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
        else
            throw new InvalidPieceMoveException();
    }
    public boolean canMove(GameVariables gameVariables, Board board) {
        boolean canMove=false;
        List<Square> occupiedSquares=board.getOccupiedSquares(gameVariables.isWhiteTurn());
        IsMyKingInCheckHandler isMyKingInCheckHandler= new IsMyKingInCheckHandler();
        for(Square square:occupiedSquares){
            for(int x = 1; x<=8&& !canMove; x++){
                for(int y = 1; y<=8 && !canMove; y++) {
                    Square toSquare=board.getSquare(x,y);
                    Move testMove=new Move(square,toSquare);
                    try{
                        square.getPiece().getBaseChain().getBeforeMoveHandler().handleMove(gameVariables,testMove);
                        square.getPiece().getBaseChain().getMoveHandler().handleMove(gameVariables,testMove);
                        isMyKingInCheckHandler.handleMove(gameVariables,testMove);
                        canMove=true;
                    }catch (Exception ignored){
                    }
                }
            }
        }
        return canMove;
    }
    public void testIfMoveIsDone(GameVariables gameVariables, Move move,boolean nextTurn){
        Board board=gameVariables.getBoard();
        board.removePiece(move.getStartingSquare());
        board.setPiece(move.getEndingSquare(),move.getStartingSquarePiece());
        if(nextTurn)
            gameVariables.virtualNextTurn();
    }
    public void undoTestIfMoveIsDone(GameVariables gameVariables, Move move,boolean nextTurn){
        Board board=gameVariables.getBoard();
        board.setPiece(move.getStartingSquare(),move.getStartingSquarePiece());
        board.setPiece(move.getEndingSquare(), move.getEndingSquarePiece());
        if(nextTurn)
            gameVariables.virtualNextTurn();
    }
}
