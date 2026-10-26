/*
* M3 - Pawn
* Created by Member 2 - jcb315
 */

package pieces;

import board.Board;
import board.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return (getColor() == Color.WHITE)
            ? "wp"
            : "bp";
        }

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