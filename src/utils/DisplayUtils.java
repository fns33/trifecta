/*
M1 - T5
Created by Member 2: jcb315
 */

package utils;

import pieces.Piece;

public class DisplayUtils {

    public static String getPieceSymbol(Piece piece) {

        if (piece == null) {
            return "";
        }

        // Temporary placeholder until Piece subclasses
        // implement toString() or getSymbol()
        return piece.toString();
    }
}