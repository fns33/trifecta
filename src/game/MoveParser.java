/* 
M2 - T1
Created by Member 2: jcb315
*/

package game;

import board.Position;

public class MoveParser {

    public static Position parsePosition(String square) {

        if (!isValidSquare(square)) {
            throw new IllegalArgumentException(
                "Invalid square: " + square
            );
        }

        square = square.toUpperCase();

        char file = square.charAt(0); // E
        char rank = square.charAt(1); // 2

        int col = file - 'A';
        int row = rank - '1';

        return new Position(row, col); // x,y coordinates
    }

public static boolean isValidSquare(String square) {

    if (square == null || square.length () != 2) {
        return false;
    }

    square = square.toUpperCase();

    char file = square.charAt(0);
    char rank = square.charAt(1);

    return file >= 'A'
        && file <= 'H'
        && rank >= '1'
        && rank <= '8';
    }    

public static Position[] parseMove(String input) {

    if(input == null) {
        throw new IllegalArgumentException("Input cannot be null.");
    }

    String[] parts = input.trim().split("\\s+");

    if (parts.length !=2) {
        throw new IllegalArgumentException(
            "Invalid format. Use [FROM] [TO]"
        );
    }

    Position from = parsePosition(parts[0]);
    Position to = parsePosition(parts[1]);

    return new Position[] { from, to };
}    
}