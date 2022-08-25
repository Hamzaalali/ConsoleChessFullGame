package chess;

public class Player {
    String name;
    boolean isWhite;
    boolean isInCheck;

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
        isInCheck=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }
    public boolean isInCheck() {

        return isInCheck;
    }
    public void setInCheck(boolean inCheck) {
        isInCheck = inCheck;
        if(isInCheck){
            if(isWhite)System.out.println("White King In Check");
            else System.out.println("Black King In Check");
        }
    }
}
