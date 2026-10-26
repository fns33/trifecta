/* 
* M2 - T1
* Created by Member 2: jcb315
*/

package game;

import board.Position;

public class MoveParser {

/*
* Converts a chess square into a position object
*
* @param square chess coordinate
* @return Position corresponding to the sqaure
* @throws IllegalArgumentException if teh square is invalid

* Parses a move in the format:
* [FROM] [TO]
*
* @param input move entered by user
* @return array containing ource and destination positions
*/    

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

/*
* Validates a chess square location
*
* @param square chess coordinate
* @return true if valid, false otherwise
*/

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
            "Invalid format. Use [FROM] [TO] (Example: E2 E4)"
        );
    }

    Position from = parsePosition(parts[0]);
    Position to = parsePosition(parts[1]);

    return new Position[] { from, to };
}    
}