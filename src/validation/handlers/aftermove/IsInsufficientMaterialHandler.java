package validation.handlers.aftermove;

import board.Board;
import board.Square;
import chess.GameStatus;
import chess.GameVariables;
import board.Move;
import validation.MoveHandler;

import java.util.List;

public class IsInsufficientMaterialHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        testIfMoveIsDone(gameVariables,move,false);
        Board board=gameVariables.getBoard();
        List<Square> myOccupiedSquares=board.getOccupiedSquares(gameVariables.isWhiteTurn());
        List<Square> enemyOccupiedSquares=board.getOccupiedSquares(!gameVariables.isWhiteTurn());
        boolean insufficientMaterial=true;
        if(!insufficientMaterial(myOccupiedSquares)){//checking for my pieces
            insufficientMaterial=false;
        }
        if(!insufficientMaterial(enemyOccupiedSquares)){//checking for enemy pieces
            insufficientMaterial=false;
        }
        if(insufficientMaterial){
            gameVariables.setGameStatus(GameStatus.INSUFFICIENTMATERIAL);// if neither the players have sufficient pieces then draw
        }
        undoTestIfMoveIsDone(gameVariables,move,false);
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables, move);
    }

    private boolean insufficientMaterial(List<Square> occupiedSquares) {
        int knightsCount = 0;
        int bishopsCount = 0;


        boolean isInsufficient=true;
        for (Square square:occupiedSquares){
            boolean isRock=square.getPiece().getPieceChar()=='R';
            boolean isPawn=square.getPiece().getPieceChar()=='P';
            boolean isQueen=square.getPiece().getPieceChar()=='Q';
            if(isRock || isPawn || isQueen){
                isInsufficient=false;
                break;
            }
            if(square.getPiece().getPieceChar()=='N'){
                knightsCount++;
            }
            if(square.getPiece().getPieceChar()=='B'){
                bishopsCount++;
            }
        }
        boolean hasKnightAndBishop=knightsCount>0 && bishopsCount>0;
        if(hasKnightAndBishop){
            isInsufficient=false;
        }
        if(knightsCount>1 || bishopsCount>1){
            isInsufficient=false;
        }
        return isInsufficient;
    }
}

