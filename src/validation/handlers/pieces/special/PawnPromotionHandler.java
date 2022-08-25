package validation.handlers.pieces.special;

import board.Square;
import chess.GameVariables;
import board.Move;
import exception.InvalidPieceMoveException;
import validation.MoveHandler;
import piece.Bishop;
import piece.Knight;
import piece.Queen;
import piece.Rock;

import java.util.Scanner;

public class PawnPromotionHandler extends MoveHandler {
    @Override
    public void handleMove(GameVariables gameVariables, Move move) {
        Square endingSquare= move.getEndingSquare();
        if(gameVariables.isWhiteTurn() &&endingSquare.getYPosition()==8){
               promote(gameVariables,move);
        }
        if(!gameVariables.isWhiteTurn() &&endingSquare.getYPosition()==1){
            promote(gameVariables,move);
        }
        if(nextHandler!=null)
            nextHandler.handleMove( gameVariables, move);
    }

    void promote(GameVariables gameVariables,Move move){
        System.out.print("Promote Pawn To :- ");
        Scanner scanner=new Scanner(System.in);
        System.out.println("1-BISHOP 2-KNIGHT 3-ROCK 4-QUEEN (CHOOSE A NUMBER OR X TO SKIP PROMOTION)");
        boolean flag=false;
        String promotion=scanner.nextLine();
        while(!flag){
            switch (promotion) {
                case "x" -> flag = true;
                case "1" -> {
                    move.setStartingSquarePiece(new Bishop(gameVariables.isWhiteTurn()));
                    flag = true;
                }
                case "2" -> {
                    move.setStartingSquarePiece(new Knight(gameVariables.isWhiteTurn()));
                    flag = true;
                }
                case "3" -> {
                    move.setStartingSquarePiece(new Rock(gameVariables.isWhiteTurn()));
                    flag = true;
                }
                case "4" -> {
                    move.setStartingSquarePiece(new Queen(gameVariables.isWhiteTurn()));
                    flag = true;
                }
            }
        }
    }
}
