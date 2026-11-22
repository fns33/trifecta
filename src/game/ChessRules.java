// Implemented by lcx2


package game;

import board.Board;
import board.Position;
import pieces.Color;
import pieces.King;
import pieces.Pawn;
import pieces.Piece;

/**
 * Utility class for evaluating chess rules.
 * Phase 3: Attacked-square and King-safety detection.
 */
public final class ChessRules {

    private ChessRules() {
        // Prevent instantiation.
    }

    /**
     * Determines whether a square is attacked by a given color.
     *
     * @param board Current board
     * @param square Square being evaluated
     * @param attackingColor Color of the attacking pieces
     * @return true if the square is attacked
     */
    public static boolean isSquareAttacked(
            Board board,
            Position square,
            Color attackingColor) {

        if (board == null || square == null || attackingColor == null) {
            return false;
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                Position from = new Position(row, col);
                Piece piece = board.getPiece(from);

                if (piece == null ||
                        piece.getColor() != attackingColor) {
                    continue;
                }

                // Pawns attack diagonally, not forward.
                if (piece instanceof Pawn) {
                    int direction =
                            attackingColor == Color.WHITE ? -1 : 1;

                    if (square.getRow() == row + direction
                            && Math.abs(square.getCol() - col) == 1) {
                        return true;
                    }

                    continue;
                }

                // Other pieces use their movement patterns.
                if (piece.possibleMoves(board).contains(square)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    /**
     * Determines whether the king of a given color is in check.
     * 
     * @param board Current board
     * @param kingColor Color of the king being evaluated
     * @return true if the king is in check. 
     */
    public static boolean isInCheck(Board board, Color kingColor){

        if (board == null || kingColor == null){  return false;  }

        // Find the king's position
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                Position position = new Position(row, col);
                Piece piece = board.getPiece(position);

                if (piece instanceof King && piece.getColor() == kingColor){
                    Color opponentColor = (kingColor == Color.WHITE) 
                                        ? Color.BLACK : Color.WHITE;
                    
                    return isSquareAttacked(board, position, opponentColor);    
                }
            }
        } 
        return false;
    }

/**
 * Determines whether a proposed move would leave
 * the moving player's King in check.
 *
 * Temporarily applies the move, evaluates the King,
 * and restores the original board afterward.
 *
 * @param board Current chessboard
 * @param from Starting square
 * @param to Destination square
 * @param movingColor Color of the moving player
 * @return true if the move leaves the King in check
 */
public static boolean wouldLeaveKingInCheck(
        Board board,
        Position from,
        Position to,
        Color movingColor) {

    if (board == null || from == null
            || to == null || movingColor == null) {
        return true;
    }

    Piece movingPiece = board.getPiece(from);
    Piece capturedPiece = board.getPiece(to);

    if (movingPiece == null
            || movingPiece.getColor() != movingColor
            || from.equals(to)
            || (capturedPiece != null
                && capturedPiece.getColor() == movingColor)) {
        return true;
    }

    // Temporarily make the proposed move.
    board.setPiece(null, from);
    board.setPiece(movingPiece, to);

    try {
        // Evaluate the King in the resulting position.
        return isInCheck(board, movingColor);
    } finally {
        // Always restore the original board state.
        board.setPiece(movingPiece, from);
        board.setPiece(capturedPiece, to);
    }
}


}
