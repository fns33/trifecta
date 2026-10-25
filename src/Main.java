/*
* Main
* CReated by Member 2: jcb315
*/

import board.Board;
import board.Position;
import pieces.Piece;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();

        // M1 - Test1
        board.display();

        // M1 - Test 4
        Position pos = new Position(0, 0);

        Piece piece = board.getPiece(pos);

        System.out.println(
            "\nPiece at " +
            ": " +
            piece.getSymbol()
        );

        //Test an empty square
        Position emptyPos =
            new Position(3, 3);
            
        Piece emptyPiece = 
            board.getPiece(emptyPos);

        System.out.println(
            "Piece at " +
            emptyPos.toChessNotation() +
            ": " +
            emptyPiece
        );
    }
}