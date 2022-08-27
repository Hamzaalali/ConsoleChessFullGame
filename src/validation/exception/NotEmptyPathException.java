package validation.exception;

public class NotEmptyPathException  extends RuntimeException {

    public NotEmptyPathException() {
        super("The Move Path is Not Empty");
    }
}