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
    * @ param board to board to display
    */

        public static void printBoard(Board board) {
        System.out.println("   A  B  C  D  E  F  G  H");

        for (int r = 0; r < 8; r++) {
            int rankNumber = 8 - r; // Maps array row 0 to Rank 8 and row 7 to Rank 1

            System.out.print(rankNumber + " ");

            for (int c = 0; c < 8; c++) {
                    Piece piece = board.getPiece(new Position(r, c));
                    // use DisplayUtils
                    String symbol = DisplayUtils.getPieceSymbol(piece);
                    if (!symbol.isEmpty()) {
                        System.out.print(symbol + " ");
                    } else {
                        // Alternates dark square (##) and light square (space) pattern
                        if ((r + c) % 2 != 0) {
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