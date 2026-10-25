package pieces;

import board.Board;
import board.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Queen chess piece.
 * The Queen combines the movement capabilities of the Rook and Bishop,
 * moving any number of unoccupied squares horizontally, vertically, or diagonally.
 * 
 * @author Repo Owner (fns33)
 */
public class Queen extends Piece {

    /**
     * Constructs a Queen piece with a specified color and starting position.
     * 
     * @param color the color of the piece (WHITE or BLACK)
     * @param position the initial position on the board
     */
    public Queen(Color color, Position position) {
        super(color, position);
    }

    /**
     * Returns the two-character text symbol representing the Queen for CLI rendering.
     * 
     * @return "wQ" for White Queen, "bQ" for Black Queen
     */
    @Override
    public String getSymbol() {
        return (getColor() == Color.WHITE) ? "wQ" : "bQ";
    }

    /**
     * Calculates all valid potential moves for the Queen from its current position.
     * Evaluates horizontal, vertical, and diagonal paths until obstructed by a piece or board edge.
     * 
     * @param board the current state of the chessboard
     * @return a List of valid Position objects the Queen can move to
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

        // 8 directions combining Rook (straight lines) and Bishop (diagonals)
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Straight lines
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Diagonals
        };

        for (int[] dir : directions) {
            int step = 1;
            while (true) {
                int targetRow = curRow + (dir[0] * step);
                int targetCol = curCol + (dir[1] * step);

                // Stop raycast if outside board boundaries
                if (targetRow < 0 || targetRow >= 8 || targetCol < 0 || targetCol >= 8) {
                    break;
                }

                Position targetPos = new Position(targetRow, targetCol);
                Piece targetPiece = board.getPiece(targetPos);

                if (targetPiece == null) {
                    // Empty square: valid move, continue raycast
                    moves.add(targetPos);
                } else {
                    // Square occupied
                    if (targetPiece.getColor() != this.getColor()) {
                        // Opponent piece: valid capture, then stop raycast
                        moves.add(targetPos);
                    }
                    // Friendly piece OR captured opponent piece: raycast obstructed
                    break;
                }

                step++;
            }
        }

        return moves;
    }
}
