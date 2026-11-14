/**
* Project Part 2
* Created by Member 2: jcb315
*/

package game;

import board.Position;
import pieces.Piece;

/**
* This class records a single move during a chess game.
* 
* Stores the piece that was moved, the starting position, 
* and the ending position of the move.
*
* Stores the piece that was captured, if any, 
* and the position of the captured piece.
* 
*/

public class MoveRecord {

    private Piece movedPiece;
    private Position startPosition;
    private Position endPosition;
    private Piece capturedPiece;
    private Position capturedPosition;

/**
* Constructor for MoveRecord class.
* 
* @param movedPiece The piece that was moved.
* @param startPosition The starting position of the moved piece.
* @param endPosition The ending position of the moved piece.
* @param capturedPiece The piece that was captured, if any.
* @param capturedPosition The position of the captured piece, if any.
*
*/    
    public MoveRecord(
        Piece movedPiece, 
        Position startPosition, 
        Position endPosition, 
        Piece capturedPiece, 
        Position capturedPosition) {

        this.movedPiece = movedPiece;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.capturedPiece = capturedPiece;
        this.capturedPosition = capturedPosition;
    }

/**
* Getter for the moved piece.
*
* @return The piece that was moved.
*/

    public Piece getMovedPiece() {
        return movedPiece;
    }

/**
* Getter for the starting position of the moved piece.
*
* @return The starting position of the moved piece.
*/    

    public Position getStartPosition() {
        return startPosition;
    }

/**
* Getter for the ending position of the moved piece.
*
* @return The ending position of the moved piece.
*/

    public Position getEndPosition() {
        return endPosition;
    }

/**
* Getter for the captured piece.
*
* @return The piece that was captured, if any.
*/

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

/**
* Getter for the position of the captured piece.
*
* @return The position of the captured piece, if any.
*/

    public Position getCapturedPosition() {
        return capturedPosition;
    }

    @Override
    public String toString() {
        String record = 
        movedPiece.getSymbol()
        + ": "
        + startPosition.toChessNotation()
        + " -> "
        + endPosition.toChessNotation();

        if (capturedPiece != null) {
            record += " (captured " + capturedPiece.getSymbol() + " at " + capturedPosition + ")";
        }
        return record;
    }
}