/*
* M1 - T5
* Created by Member 2: jcb315
* Utility methods for chess pieces
* Converts Piece objects into displayed text symbols
 */

package utils;

import pieces.Piece;

/*
* Return the display symbol for a chess piece
*
* @param piece chess piece
* @return piece display symbol
*/

public class DisplayUtils {

    public static String getPieceSymbol(Piece piece) {

        if (piece == null) {
            return "";
        }

        return piece.getSymbol()
    }
}