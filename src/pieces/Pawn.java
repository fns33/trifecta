/*
* M3 - Pawn
* Created by Member 2 - jcb315
 */

package pieces;

import board.Board;
import board.Position;
import java.util.ArrayList;
import java.util.List:

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

    public List<Position> possibleMoves(Board board) {

        List<Position> moves = new ArrayList<>();

        // TODO: Implement Pawn movement

        return moves;

    }
 }