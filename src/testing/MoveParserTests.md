/*
* Move Parser Test
* M1 - Test 1
* M1 - Test 4
*/

package testing;

import board.Board;
import board.Position;
import pieces.Piece;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();

        //M1-Test1
        board.display();

        // M1-Test4
        Position pos = new Position(0, 0);

        System.out.Println(
            "\nPiece at " +
            pos.toChessNotation() +
            ": " +
            piece.getSymbol()
        );

        //Empty square
        Position emptyPos =
            new Position(3, 3);

        Piece emptyPiece = 
            board.getPiece(emptyPos);

        System.out.println(emptyPiece);  
    }
}