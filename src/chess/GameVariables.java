package chess;

import board.Board;
import piece.Piece;
import piece.King;

public class GameVariables {
    Board board;
    GameStatus gameStatus;
    Player whitePlayer;
    Player blackPlayer;
    boolean isWhiteTurn;
    Piece whiteKing;
    Piece blackKing;



    int turnsCount;
    public GameVariables(String whitePlayerName,String blackPlayerName){
        board = new Board();
        whiteKing=new King(true);
        blackKing=new King(false);
        board.initializeBoard(whiteKing,blackKing);
        gameStatus=GameStatus.RUNNING;
        this.whitePlayer=new Player(whitePlayerName,true);
        this.blackPlayer=new Player(blackPlayerName,false);
        isWhiteTurn=true;
        turnsCount=1;
    }

    public Player getCurrentPlayer(){
        if(isWhiteTurn)
            return whitePlayer;
        else
            return blackPlayer;
    }

    public Piece getEnemyPlayerKing(){
        if(!isWhiteTurn)
            return whiteKing;
        else
            return blackKing;
    }
    public Player getEnemyPlayer(){
        if(!isWhiteTurn)
            return whitePlayer;
        else
            return blackPlayer;
    }

    public Piece getCurrentPlayerKing(){
        if(isWhiteTurn)
            return whiteKing;
        else
            return blackKing;
    }
    public void nextTurn(){
        isWhiteTurn=!isWhiteTurn;
        turnsCount++;
    }
    public void virtualNextTurn(){
        isWhiteTurn=!isWhiteTurn;
    }
    public Board getBoard() {
        return board;
    }


    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }
    public void setGameStatus(GameStatus gameStatus){
        this.gameStatus=gameStatus;
    }

    public int getTurnsCount() {
        return turnsCount;
    }
}
