/*
* M3 - Knight
* Created by Member 2 - jcb315
* Knight Chess Piece
* L-shaped moves: 
* 2 squares in one direction and 1 square perpendicular
* Jumping capability over another piece
 */

package pieces;

import board.Board;
import board.Position;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    
    /*
    * Returns the display symbol for night
    * @return "wN" for white knight or "bN" for black knight
    */

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return (getColor() == Color.WHITE)
            ? "wN"
            : "bN";
    }

    /*
    * Calculate Knight moves fro current position
    * Jumping capabilities 
    * Cannot move onto occupied square of same color
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

        int [] [] offsets = {
            {-2, -1},
            {-2,  1},
            {-1, -2},
            {-1,  2},
            { 1, -2},
            { 1,  2},
            { 2, -1},
            { 2,  1}
        };

        for (int[] offset : offsets) {

            int newRow = row + offset[0];
            int newCol = col + offset[1];

            if (Position.isValid(newRow, newCol)) {

                Position target = 
                    new Position(newRow, newCol);

                Piece occupant = 
                    board.getPiece(target);

                if (occupant == null ||
                    occupant.getColor() != getColor()) {

                    moves.add(target);
                }
            }
        }

        return moves;
    }
    
}
