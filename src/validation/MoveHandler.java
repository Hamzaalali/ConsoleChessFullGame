package validation;

import board.Board;
import board.Square;
import chess.GameVariables;
import board.Move;
import validation.exception.InvalidPieceMoveException;
import validation.handlers.beforemove.IsMyKingInCheckHandler;

import java.util.List;

public abstract class MoveHandler {

    protected MoveHandler nextHandler = null;

    //this method will set the next handler
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
    //this will check if the current player has any valid move - user by checkmate handler and stalemate handler
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
                        square.getPiece().getPieceChain().getBeforeMoveHandler().handleMove(gameVariables,testMove);
                        square.getPiece().getPieceChain().getMoveHandler().handleMove(gameVariables,testMove);
                        isMyKingInCheckHandler.handleMove(gameVariables,testMove);
                        canMove=true;
                    }catch (Exception ignored){
                    }
                }
            }
        }
        return canMove;
    }
    //this method will simulate if the move is done - used to check for things after the move
    public void testIfMoveIsDone(GameVariables gameVariables, Move move,boolean nextTurn){
        Board board=gameVariables.getBoard();
        board.removePiece(move.getStartingSquare());
        board.setPiece(move.getEndingSquare(),move.getStartingSquarePiece());
        if(nextTurn)
            gameVariables.virtualNextTurn();
    }
    //this method will be used after the test if move is done method to return every thing as it was
    public void undoTestIfMoveIsDone(GameVariables gameVariables, Move move,boolean nextTurn){
        Board board=gameVariables.getBoard();
        board.setPiece(move.getStartingSquare(),move.getStartingSquarePiece());
        board.setPiece(move.getEndingSquare(), move.getEndingSquarePiece());
        if(nextTurn)
            gameVariables.virtualNextTurn();
    }
}
