/*
* M3 - Pawn
* Created by Member 2 - jcb315
* Pawn Chess Piece
* Diagonal moves:
* 2 sqaures forward
 */

package pieces;

import board.Board;
import board.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    /* 
    * Pawn choice by color and position
    * @param color the piece color
    * @param position the starting position
     */

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    /*
    * Return symbol for Pawn
    * @return "wp" for white pawn or "bp" for black pawn
    */

    @Override
    public String getSymbol() {
        return (getColor() == Color.WHITE)
            ? "wp"
            : "bp";
        }

    /*
    * Calculates Pawn moves from current position
    * - One-squre move
    * - two-sqaure opening moves
    * - Diagnal capture
    * @param board the current board state
    * @return a list of valid destination positions
    */ 
   
    @Override 
    public List<Position> possibleMoves(Board board) {

        List<Position> moves = new ArrayList<>();

        Position current = getCurrentPosition();

        if (current == null || board == null) {
            return moves;
        }

        int row = current.getRow();
        int col = current.getCol();

        /*
        * White pawns move toward smaller row numbers.
        * Blac pawns move toward larger row numbers.
        */

        int direction = 
            (getColor() == Color. WHITE)
            ? -1
            : 1;

        /*
        * Starting row:
        * White = 6
        * Black = 1
        */ 

        int startingRow = 
            (getColor() == Color.WHITE)
            ? 6
            : 1;

        // Foward one square
        int nextRow = row + direction;

        if (Position.isValid(nextRow, col)) {

            Position forward = 
                new Position(nextRow, col);

            if (board.getPiece(forward) == null) {

                moves.add(forward);

                // Foward two squares from starting row 
                if (row == startingRow) {

                    int doubleRow = 
                        row + (2 * direction);

                    Position doubleForward = 
                        new Position(doubleRow, col);

                    if (board.getPiece(doubleForward) == null) {
                        moves.add(doubleForward);
                    }
                }
            }
        }

        // Diagnal capture left
        int leftCol = col - 1;

        if (Position.isValid(nextRow, leftCol)) {

            Position leftCapture = 
                new Position(nextRow, leftCol);

            Piece occupant = 
                board.getPiece(leftCapture);

            if (occupant != null &&
                occupant.getColor() != getColor()) {

                moves.add(leftCapture);
            }
        }

        //Diagonal capture right 
        int rightCol = col +1;

        if (Position.isValid(nextRow, rightCol)) {

            Position rightCapture = 
                new Position(nextRow, rightCol);

            Piece occupant = 
                board.getPiece(rightCapture);

            if (occupant != null &&
                occupant.getColor() != getColor()) {

                moves.add(rightCapture);
            }
        }

        return moves;

    }
 }