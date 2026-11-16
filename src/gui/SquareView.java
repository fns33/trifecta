
package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a single interactive square on the 8x8 visual chessboard.
 * Manages individual tile background colors, piece symbol display,
 * and selection highlight states.
 *
 * @author fns33
 */
public class SquareView extends JButton {

    private final int row;
    private final int col;
    private final Color defaultBackground;

    private static final Color HIGHLIGHT_COLOR =
        new Color(255, 255, 150); // Soft Yellow

    /**
     * Constructs a SquareView for a given grid coordinate.
     *
     * @param row 0-indexed row coordinate (0 to 7)
     * @param col 0-indexed column coordinate (0 to 7)
     */
    public SquareView(int row, int col) {

        this.row = row;
        this.col = col;

        // Determine alternating light/dark square colors
        boolean isLightSquare = (row + col) % 2 == 0;

        this.defaultBackground = isLightSquare
            ? new Color(240, 217, 181)  // Light beige/wood
            : new Color(181, 136, 99);  // Dark brown/wood

        initSquare();
    }

    /**
     * Initializes the square's visual properties.
     */
    private void initSquare() {

        setBackground(defaultBackground);
        setFocusPainted(false);
        setBorderPainted(false);

        // made the font smaller and serif to better fit the chess symbols
        // AUTHOR: lcx2
        setFont(new Font("Serif", Font.PLAIN, 18));
        setOpaque(true);
    }

    /**
     * Updates the text display symbol representing the piece on this square.
     *
     * @param symbol text representation (e.g. "wK", "bp", or empty string)
     */
    /**
 * Updates the symbol and text color displayed on this square.
 */
    public void setPieceSymbol(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            setText("");
            return;
        }

        // Map piece codes to Unicode chess symbols
        String displaySymbol = switch (symbol) {
            case "wK" -> "♔";
            case "bK" -> "♚";
            case "wQ" -> "♕";
            case "bQ" -> "♛";
            case "wR" -> "♖";
            case "bR" -> "♜";
            case "wB" -> "♗";
            case "bB" -> "♝";
            case "wN" -> "♘";
            case "bN" -> "♞";
            case "wp" -> "♙";
            case "bp" -> "♟";
            default -> symbol;
        };

        setText(displaySymbol);

        // Set text color based on piece color
        if (symbol.startsWith("w")) {
            setForeground(new Color(240, 240, 240));
        } else if (symbol.startsWith("b")) {
            setForeground(new Color(0, 0, 0));
        }
    }

    /**
     * Highlights or un-highlights the square to visually indicate selection.
     *
     * @param selected true to apply selection highlight,
     *                 false to restore default color
     */
    public void setSelected(boolean selected) {

        if (selected) {
            setBackground(HIGHLIGHT_COLOR);
        } else {
            setBackground(defaultBackground);
        }
    }

    /**
     * Returns the row coordinate of this square.
     *
     * @return the row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column coordinate of this square.
     *
     * @return the column index
     */
    public int getCol() {
        return col;
    }
}
