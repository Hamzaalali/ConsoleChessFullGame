package chess;

import board.Move;
import exception.InvalidInputFormatException;
import exception.PieceDoesntExistException;
import piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ChessGame {
    Scanner scanner;
    GameVariables gameVariables;
    public void start() {
        scanner = new Scanner(System.in);
        System.out.print("Enter the white player name :");
        String whitePlayerName=scanner.nextLine();
        System.out.print("Enter the black player name :");
        String blackPlayerName=scanner.nextLine();
        gameVariables=new GameVariables(whitePlayerName,blackPlayerName);
        while(gameVariables.getGameStatus()==GameStatus.RUNNING){
            if(gameVariables.isWhiteTurn())
                System.out.print("Enter next move (white player):");
            else
                System.out.print("Enter next move (black player):");
            String movement=scanner.nextLine();
            try{
                move(movement);
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Try Again,");
            }
        }
    }
    public void move(String move) {
        move = move.toLowerCase();
        if(move.length()<10)
            throw  new InvalidInputFormatException();
        if(!validateMove(move.substring(0,10)))
            throw  new InvalidInputFormatException();
        String startingSquareString =move.split(" ")[1];
        String endingSquareString =move.split(" ")[2];
        if(Objects.equals(startingSquareString, endingSquareString))
            throw  new InvalidInputFormatException();
        Move newMove=new Move(gameVariables,startingSquareString,endingSquareString);
        Piece movedPiece =newMove.getStartingSquare().getPiece();
        if(movedPiece==null){
            throw new PieceDoesntExistException();
        }
        movedPiece.getBaseChain().pieceChainHandler(gameVariables, newMove);
    }
    public boolean validateMove(String move){
        return move.matches("move [a-h][1-8] [a-h][1-8]");
    }
}
