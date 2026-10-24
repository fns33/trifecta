package pieces;

import board.Board;
import board.Position;
import java.util.ArrayList; 
import java.util.List;

public class Bishop extends Piece {
    public Bishop (Color color, Position position){super(color, position);}

    @Override
    public String getSymbol(){
        return getColor() == Color.WHITE ? "wB" : "bB";}

    @Override
    public List<Position> possibleMoves(Board board){
        List<Position> moves = new ArrayList<>();

        int row = getCurrentPosition().getRow();
        int col = getCurrentPosition().getCol();

        int [][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int [] direction : directions){
            int r = row + direction[0];
            int c = col + direction[1];

            while (Position.isValid(r,c)){
                Position next = new Position(r,c);
                Piece occupant = board.getPiece(next);

                if (occupant == null){moves.add(next);}
                else {
                    if (occupant.getColor() != getColor()){
                        moves.add(next);
                    }
                    break;
                }

                r += direction[0];
                c += direction[1];

            }

        }
        return moves;
    }
}