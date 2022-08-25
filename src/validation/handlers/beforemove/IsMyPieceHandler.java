package validation.handlers.beforemove;

import board.Square;
import chess.GameVariables;
import board.Move;
import exception.CantMoveEnemyPieceException;
import validation.MoveHandler;

public class IsMyPieceHandler  extends MoveHandler {

    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Square startingSquare=move.getStartingSquare();
        boolean isWhitePiece = gameVariables.getBoard().getPiece(startingSquare).isWhite();
        boolean isWhiteTurn = gameVariables.isWhiteTurn();
        boolean isNotMyPiece=isWhitePiece!=isWhiteTurn;
        if (isNotMyPiece) {
            throw new CantMoveEnemyPieceException();
        }
        if (nextHandler != null)
            nextHandler.handleMove(gameVariables,move);
    }
}