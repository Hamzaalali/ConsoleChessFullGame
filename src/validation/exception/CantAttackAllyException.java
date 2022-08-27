package validation.exception;

public class CantAttackAllyException extends RuntimeException {

    public CantAttackAllyException(){
        super("You Can't Attack Your Own Pieces");
    }
}
