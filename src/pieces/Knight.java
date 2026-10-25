/*
* M3 - Knight
* Created by Member 2 - jcb315
 */

package pieces;

import board.Board;
import board.Position;
import java.util.ArrayList;
import java.util.List:

public class Knight extends Piece {
    
    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        return (getColor() == Color.WHITE)
            ? "wN"
            : "bN";
    }

    public List<Position> PossibleMoves(Board board) {

        List<Position> moves = New ArrayList<>();

        // TODO: Implement Knight move

        return moves;
    }
    
}
