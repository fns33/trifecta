package pieces;

import board.Board;
import board.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    //The Rook class extends the Piece class and represents a rook piece in chess.
    public Rook(Color color, Position position){super(color, position);}
    
    //The getSymbol method returns the symbol for the rook piece based on its color.
    @Override 
    public String getSymbol(){
        return getColor() == Color.WHITE ? "wR" : "bR";
    }

    //The possibleMoves method returns a list of all possible moves for the rook 
    // piece on the given board.
    @Override
    public List<Position> possibleMoves(Board board){
        List<Position> moves = new ArrayList<>();

        int row = getCurrentPosition().getRow();
        int col = getCurrentPosition().getCol();

        //View the adjacent squares under the rook's current position
        //  in all four directions (up, down, left, right)

        int [][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // task: for each direction, keep moving in that direction until you hit 
        // the edge of the board or another piece
        for (int [] direction : directions){
            int r = row + direction[0];
            int c = col + direction[1];

            // while the position is valid, keep moving in that direction
            while (Position.isValid(r,c)){
                Position next = new Position(r,c);
                Piece occupant = board.getPiece(next);

                // if the square is empty, add it to the list of possible moves
                // if the square is occupied by an opponent's piece, 
                // add it to the list of possible moves and stop moving in that direction.
                if (occupant == null){moves.add(next);}
                else {
                    if (occupant.getColor() != getColor()){
                        moves.add(next);
                    }
                    break;
                }

                // move to the next square in that direction
                

                r += direction[0];
                c += direction[1];

            }

        }

        return moves;
        
    }


}