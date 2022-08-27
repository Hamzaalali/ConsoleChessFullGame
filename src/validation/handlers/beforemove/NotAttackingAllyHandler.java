package validation.handlers.beforemove;


import board.Square;
import chess.GameVariables;
import board.Move;
import validation.exception.CantAttackAllyException;
import validation.MoveHandler;
import piece.Piece;

public class NotAttackingAllyHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Square endingSquare= move.getEndingSquare();
        Piece endingSquarePiece=gameVariables.getBoard().getPiece(endingSquare);
        if(endingSquarePiece!=null && endingSquarePiece.isWhite() == gameVariables.isWhiteTurn()){
            throw new CantAttackAllyException();
        }
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables,move);
    }
}
