package pieces;

import board.Board;
import board.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a King chess piece.
 * The King moves one square in any direction (vertically, horizontally, or diagonally).
 * 
 * @author Repo Owner (fns33)
 */
public class King extends Piece {

    /**
     * Constructs a King piece with a specified color and starting position.
     * 
     * @param color the color of the piece (WHITE or BLACK)
     * @param position the initial position on the board
     */
    public King(Color color, Position position) {
        super(color, position);
    }

    /**
     * Returns the two-character text symbol representing the King for CLI rendering.
     * 
     * @return "wK" for White King, "bK" for Black King
     */
    @Override
    public String getSymbol() {
        return (getColor() == Color.WHITE) ? "wK" : "bK";
    }

    /**
     * Calculates all valid potential moves for the King from its current position.
     * Validates board boundary limits and prevents friendly piece captures.
     * 
     * @param board the current state of the chessboard
     * @return a List of valid Position objects the King can move to
     */
    @Override
    public List<Position> possibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        Position current = getCurrentPosition();
        if (current == null || board == null) {
            return moves;
        }

        int curRow = current.getRow();
        int curCol = current.getCol();

        // 8 possible directional offsets surrounding the King
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
        };

        for (int[] dir : directions) {
            int targetRow = curRow + dir[0];
            int targetCol = curCol + dir[1];

            // Verify target position is within 8x8 board boundaries
            if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {
                Position targetPos = new Position(targetRow, targetCol);
                Piece targetPiece = board.getPiece(targetPos);

                // Square is valid if empty OR occupied by an opponent's piece
                if (targetPiece == null || targetPiece.getColor() != this.getColor()) {
                    moves.add(targetPos);
                }
            }
        }

        return moves;
    }
}
