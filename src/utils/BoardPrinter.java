/*
* M1 - T4
* Created by Member 2: jcb315
* Current board state
* Displays labels, chess pieces and empty squares
*/

package utils;

import board.Board;
import board.Position;
import pieces.Piece;

public class BoardPrinter {

    /*
    * Print the board to the console
    * Displays:
    * - File Labels (A-H)
    * - Rank Labels (1-8)
    * - Piece Symbols
    * - Empty Squres
    * @ param board to board display
    */
   
    public static void print(Board board) {

        System.out.println("  A  B  C  D  E  F  G  H");
        System.out.println()

        for (int row = 7; row >=0; row --) {
            
            System.out.print((row + 1) + " ") ;

            for (int col = 0; col < 8; col++) {

                Position pos = new Position(row, col);
                Piece piece = board.getPiece(pos);

                );

                if (piece != null) {
                    System.out.print(
                        DisplayUtils.getPieceSymbol(piece) + " "
                    );

                }

                else {
                    if ((row + col) % 2 ==0) {
                        System.out.print("## ");
                    } else {
                        System.out.print("   ");
                    }
                }
            }

            System.out.println();
        }
    }
}