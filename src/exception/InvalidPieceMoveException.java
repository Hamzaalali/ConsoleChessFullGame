package exception;

public class InvalidPieceMoveException  extends RuntimeException {

    public InvalidPieceMoveException() {
        super("Invalid Piece Move");
    }
}