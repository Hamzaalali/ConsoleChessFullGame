package exception;

public class CantMoveEnemyPieceException extends RuntimeException {

    public CantMoveEnemyPieceException(){
        super("You Can't Move Enemy Piece");
    }
}
