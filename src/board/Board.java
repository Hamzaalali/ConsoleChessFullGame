package board;

import piece.Piece;
import piece.Bishop;
import piece.Knight;
import piece.Queen;
import piece.Pawn;
import piece.Rock;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Square[][] squares=new Square[9][9];
    public Board(){
        for (int yPosition=1;yPosition<=8;yPosition++){
            for(int xPosition=1;xPosition<=8;xPosition++){
                squares[yPosition][xPosition]=new Square(xPosition,yPosition);//initialize board squares to null
            }
        }
    }
    public Square getPieceSquare(Piece piece){
        for(int x=1;x<=8;x++){
            for(int y=1;y<=8;y++){
                if(squares[x][y].getPiece()==piece){
                    return squares[x][y];
                }
            }
        }
        return null;
    }
    public Piece getPiece(Square square){
        if(squares[square.getYPosition() ][square.getXPosition()].getPiece()==null){
            return null;
        }
        return squares[square.getYPosition() ][square.getXPosition()].getPiece();
    }
    public Piece getPiece(int xPosition,int yPosition){
        Piece temp=squares[yPosition][xPosition].getPiece();
        if(temp==null){
            return null;
        }
        return temp;
    }

    public Piece removePiece(Square square){
        Piece temp=squares[square.getYPosition()][square.getXPosition()].getPiece();
        squares[square.getYPosition()][square.getXPosition()].setPiece(null);
        return temp;
    }
    public Piece removePiece(int xPosition,int yPosition){
        Piece temp=squares[yPosition][xPosition].getPiece();
        squares[yPosition][xPosition].setPiece(null);
        return temp;
    }
    public void setPiece(Square square,Piece piece){
        square.setPiece(piece);
    }
    public void setPiece(int xPosition,int yPosition ,Piece piece){
        squares[yPosition][xPosition].setPiece(piece);
    }
    public Square getSquare(int xPosition,int yPosition){
        return squares[yPosition][xPosition];
    }

    public void initializeBoard(Piece whiteKing,Piece blackKing){
        // this will initialize all pieces to the board , white and black
        for(int xPosition=1;xPosition<=8;xPosition++){
            setPiece(xPosition,2,new Pawn(true));
            setPiece(xPosition,7,new Pawn(false));
            if(xPosition==1||xPosition==8){
                setPiece(xPosition,1,new Rock(true));
                setPiece(xPosition,8,new Rock(false));
            }
            if(xPosition==2||xPosition==7){
               setPiece(xPosition,1,new Knight(true));
               setPiece(xPosition,8,new Knight(false));
            }
            if(xPosition==3||xPosition==6){
                setPiece(xPosition,1,new Bishop(true));
                setPiece(xPosition,8,new Bishop(false));
            }
            if(xPosition==4){
                setPiece(xPosition,1,new Queen(true));
                setPiece(xPosition,8,new Queen(false));
            }
            if(xPosition==5){

                setPiece(xPosition,1,whiteKing);
                setPiece(xPosition,8,blackKing);
            }
        }
        printBoard();
    }
    public void printBoard(){
        //prints the board
        System.out.println("-------------------------------------------------------------------");
        for(int rowNumber=9;rowNumber>=0;rowNumber--){
            if(rowNumber==9 || rowNumber==0){
                drawBoardCharBoard();
                continue;
            }
            drawRaw(rowNumber);
        }
        System.out.println("-------------------------------------------------------------------");
    }
    void drawRaw(int rowNumber){
        boolean isWhiteSquare=false;
        String color;
        if(rowNumber%2==0)
            isWhiteSquare=true;
        for (int columnNumber=0;columnNumber<=9;columnNumber++,isWhiteSquare=!isWhiteSquare){
            if(columnNumber==0||columnNumber==9){
                System.out.print(ConsoleColors.WHITE+rowNumber+"  ");
                continue;
            }
            if(!isWhiteSquare)
                color=ConsoleColors.WHITE;
            else
                color=ConsoleColors.BLACK;
            Piece piece=squares[rowNumber][columnNumber].getPiece();
            if(piece==null){
                System.out.print(color+"== ");
                continue;
            }
            if(piece.isWhite())
                System.out.print(ConsoleColors.WHITE+'W');
            else
                System.out.print(ConsoleColors.BLACK+'B');
            System.out.print(piece.getPieceChar()+" ");
        }
        System.out.println();
    }
    void drawBoardCharBoard(){
        System.out.print("   ");
        for(int xPosition = 1;xPosition<=8;xPosition++){
            System.out.print(ConsoleColors.WHITE+(char)(xPosition+'a'-1)+"  ");
        }
        System.out.println();
    }
    public List<Square> getOccupiedSquares(boolean isWhitePieces){
        //will return all squares that have pieces on them based on the color of the piece
        List<Square>  occupiedSquares=new ArrayList<>();
        for(int x=1;x<=8 ;x++) {
            for (int y = 1; y <= 8; y++) {
                if(squares[y][x].getPiece()==null){
                   continue;
                }
                if(squares[y][x].getPiece().isWhite()==isWhitePieces){
                    occupiedSquares.add(squares[y][x]);
                }
            }
        }
        return occupiedSquares;
    }
}
