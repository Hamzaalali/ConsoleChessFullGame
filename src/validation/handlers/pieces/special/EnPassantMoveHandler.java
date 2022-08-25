package validation.handlers.pieces.special;

import board.Board;
import board.Square;
import chess.GameVariables;
import board.Move;
import validation.MoveHandler;
import board.Direction;

import static java.lang.Math.abs;

public class EnPassantMoveHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Square startingSquare=move.getStartingSquare();
        Square endingSquare= move.getEndingSquare();
        Board board=gameVariables.getBoard();
        Square enPassantSquare=board.getSquare(endingSquare.getXPosition(),startingSquare.getYPosition());
        if(enPassantSquare.getPiece()==null){
            checkForNextHandler(gameVariables,move);
        }
        if(enPassantSquare.getPiece().getMovesCount()!=1 ){
            checkForNextHandler(gameVariables,move);
        }
        if(enPassantSquare.getPiece().getLastMoveTurn()!=gameVariables.getTurnsCount()-1){
            checkForNextHandler(gameVariables,move);
        }
        boolean isEnPassant;
        if(gameVariables.isWhiteTurn()){
            isEnPassant=isEnPassant(move,Direction.NORTHEAST,Direction.NORTHWEST);
        }else{
            isEnPassant=isEnPassant(move,Direction.SOUTHEAST,Direction.SOUTHWEST);
        }
        if(isEnPassant){
            enPassantSquare.setPiece(null);
            System.out.println("EN PASSANT !");
            return;
        }
        checkForNextHandler(gameVariables,move);
    }
    boolean isEnPassant(Move move,Direction attackRightDirection,Direction attackLeftDirection){
        return move.getDirection() == attackRightDirection || move.getDirection() == attackLeftDirection && move.getSteps() == 1;
    }
}
