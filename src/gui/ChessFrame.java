
package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window for the Phase 2 Java Swing Chess Application.
 * Manages the top-level JFrame container, layout configuration, and
 * endgame notification dialogs.
 *
 * @author fns33
 */
public class ChessFrame extends JFrame {

    private BoardPanel boardPanel;

    /**
     * Constructs and initializes the main Chess GUI window.
     */
    public ChessFrame() {
        super("CS 3354 Chess Game — Phase 2");
        initUI();
    }

    /**
     * Configures window layout, component arrangement, and display properties.
     */
    private void initUI() {

        // Set close operation to terminate app upon exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Instantiate and add the 8x8 BoardPanel to the window center
        boardPanel = new BoardPanel(this);
        add(boardPanel, BorderLayout.CENTER);

        // Window dimensions and positioning
        setSize(700, 720);
        setMinimumSize(new Dimension(600, 620));
        setLocationRelativeTo(null); // Center window on screen
        setResizable(true);
    }

    /**
     * Displays a winner notification pop-up when a King is captured
     * and terminates the application upon dismissal.
     *
     * @param winningColor the color or name of the victorious player
     *                     ("White" or "Black")
     */
    public void showWinnerDialog(String winningColor) {

        JOptionPane.showMessageDialog(
            this,
            "👑 Game Over! " + winningColor
                + " player captures the King and wins!",
            "Winner Declared",
            JOptionPane.INFORMATION_MESSAGE
        );

        System.exit(0);
    }

    /**
     * Convenience getter for the internal board panel component.
     *
     * @return the active BoardPanel instance
     */
    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    /**
     * Launches the GUI application on the Event Dispatch Thread (EDT).
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ChessFrame frame = new ChessFrame();
            frame.setVisible(true);
        });
    }
}
