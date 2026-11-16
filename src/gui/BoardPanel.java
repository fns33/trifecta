
package gui;

import board.Board;
import board.Position;
import pieces.Piece;
import utils.DisplayUtils;

import javax.swing.*;
import java.awt.*;

/**
 * 8x8 Grid Panel container for displaying the interactive visual chessboard.
 * Integrates visual SquareView components with the underlying Board data model.
 *
 * @author fns33 
 */
public class BoardPanel extends JPanel {

    private final ChessFrame parentFrame;
    private final SquareView[][] squares = new SquareView[8][8];
    private final ChessMouseController mouseController;
    private Board board;

    /**
     * Constructs the 8x8 BoardPanel grid container.
     *
     * @param parentFrame reference to the main ChessFrame application window
     */
    public BoardPanel(ChessFrame parentFrame) {

        this.parentFrame = parentFrame;
        this.board = new Board();
        this.mouseController = new ChessMouseController(this);

        // Initialize starting 8x8 piece layout
        setLayout(new GridLayout(8, 8));

        initializeGrid();
        refreshBoard();
    }

    /**
     * Instantiates the 64 SquareView buttons in rank-major order
     * (Rank 8 at top down to Rank 1).
     */
    private void initializeGrid() {

        // Iterate from row 7 (Rank 8) down to row 0 (Rank 1)
        // for standard visual orientation.
        for (int row = 7; row >= 0; row--) {

            for (int col = 0; col < 8; col++) {

                SquareView square = new SquareView(row, col);

                squares[row][col] = square;

                // Mouse listener Integration @Austhor: lcx2
                final int r = row;
                final int c = col;

                square.addActionListener(
                    e -> mouseController.handleSquareClick(r, c)
                );

                add(square);
            }
        }
    }

    /**
     * Syncs visual SquareView symbols with the current state
     * of the backend Board model.
     */
    public void refreshBoard() {

        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < 8; col++) {

                Position pos = new Position(row, col);
                Piece piece = board.getPiece(pos);

                String symbol = DisplayUtils.getPieceSymbol(piece);

                squares[row][col].setPieceSymbol(symbol);
                squares[row][col].setSelected(false);
            }
        }
    }

    public ChessMouseController getMouseController() {
        return mouseController;
    }

    /**
     * Replaces the active board state model and refreshes the display.
     *
     * @param board the new Board model state
     */
    public void setBoard(Board board) {

        this.board = board;
        refreshBoard();
    }

    /**
     * Returns the current board model.
     *
     * @return the active Board instance
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns a square at the specified coordinates.
     *
     * @param row the row index
     * @param col the column index
     * @return the SquareView at the given coordinates
     */
    public SquareView getSquare(int row, int col) {
        return squares[row][col];
    }

    /**
     * Returns the parent ChessFrame.
     *
     * @return the main application window
     */
    public ChessFrame getParentFrame() {
        return parentFrame;
    }
}
