package validation.handlers.aftermove;

import board.Square;
import chess.GameVariables;
import board.Move;
import validation.MoveHandler;
import piece.Piece;

public class CompleteTheMoveHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Square startingSquare=move.getStartingSquare();
        Square endingSquare= move.getEndingSquare();
        gameVariables.getBoard().getPiece(startingSquare).moved();
        gameVariables.getBoard().getPiece(startingSquare).setLastMoveTurn(gameVariables.getTurnsCount());
        Piece movedPiece= gameVariables.getBoard().removePiece(startingSquare);
        gameVariables.getBoard().setPiece(endingSquare,movedPiece);
        System.out.println();
        gameVariables.getBoard().printBoard();
        gameVariables.nextTurn();
    }
}
