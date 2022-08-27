package validation.exception;

public class PieceDoesntExistException extends RuntimeException {

    public PieceDoesntExistException() {
        super("Piece Doesn't Exist");
    }
}