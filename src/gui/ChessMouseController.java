

package gui;

import board.Board;
import board.Position;
import game.GameState;
import pieces.Piece;

/**
 * Handles mouse interactions for the chessboard GUI.
 * Delegates movement, captures, and turn management to GameState.
 */
public class ChessMouseController {

    private final BoardPanel boardPanel;
    private Position selectedPosition;

    /**
     * Creates a controller for the chessboard.
     *
     * @param boardPanel panel receiving mouse interactions
     */
    public ChessMouseController(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        this.selectedPosition = null;
    }

    /**
     * Processes clicks on chessboard squares.
     *
     * @param row clicked square's row
     * @param col clicked square's column
     */
    public void handleSquareClick(int row, int col) {

        GameState gameState =
            boardPanel.getParentFrame().getGameState();

        if (gameState == null || gameState.isGameOver()) {
            return;
        }

        Board board = gameState.getBoard();
        Position clickedPosition = new Position(row, col);
        Piece clickedPiece = board.getPiece(clickedPosition);

        // Select a piece belonging to the current player.
        if (selectedPosition == null) {

            if (clickedPiece != null &&
                clickedPiece.getColor() == gameState.getCurrentTurn()) {

                selectedPosition = clickedPosition;
                boardPanel.getSquare(row, col).setSelected(true);
            }

            return;
        }

        // Clicking the selected square cancels selection.
        if (selectedPosition.getRow() == row &&
            selectedPosition.getCol() == col) {

            clearSelection();
            return;
        }

        // Clicking another friendly piece changes the selection.
        if (clickedPiece != null &&
            clickedPiece.getColor() == gameState.getCurrentTurn()) {

            clearSelection();
            selectedPosition = clickedPosition;
            boardPanel.getSquare(row, col).setSelected(true);
            return;
        }

        // GameState handles movement, captures, and turn updates.
        boolean moveSuccessful =
            gameState.executeMove(selectedPosition, clickedPosition);

        if (moveSuccessful) {
            clearSelection();
            boardPanel.getParentFrame().refreshGameDisplay();
        }
    }

    /**
     * Clears a selection after resetting, loading, or undoing a move.
     */
    public void resetSelection() {
        clearSelection();
    }

    /**
     * Removes the current selection highlight.
     */
    private void clearSelection() {

        if (selectedPosition != null) {
            boardPanel.getSquare(
                selectedPosition.getRow(),
                selectedPosition.getCol()
            ).setSelected(false);
        }

        selectedPosition = null;
    }
}
