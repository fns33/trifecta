
package gui;

import javax.swing.*;
import java.awt.*;
import game.GameState;
import game.GameStorage;
import java.io.File;

/**
 * Main application window for the Phase 2 Java Swing Chess Application.
 * Manages the top-level JFrame container, layout configuration, and
 * endgame notification dialogs.
 *
 * @author fns33
 */
public class ChessFrame extends JFrame {

    private BoardPanel boardPanel;
    private GameState gameState;
    private GameHistoryPanel historyPanel;
    private GameMenuBar gameMenuBar;
    private boolean winnerDialogShown = false;

    /**
     * Constructs and initializes the main Chess GUI window.
     */
    public ChessFrame() {
        super("CS 3354 Chess Game — Phase 2");

        gameState = new GameState();

        initUI();
        connectActions();
    }

    /**
     * Configures window layout, component arrangement, and display properties.
     */
    private void initUI() {

        // Set close operation to terminate app upon exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Create menu bar
        gameMenuBar = new GameMenuBar();
        setJMenuBar(gameMenuBar);

        // Create game history panel
        historyPanel = new GameHistoryPanel();
        historyPanel.setPreferredSize(new Dimension(230, 0));
        add(historyPanel, BorderLayout.EAST);

        // Instantiate and add the 8x8 BoardPanel to the window center
        boardPanel = new BoardPanel(this);

        // Connect GUI board to GameState
        boardPanel.setBoard(gameState.getBoard());

        add(boardPanel, BorderLayout.CENTER);

        // Window dimensions and positioning
        setSize(700, 720);
        setMinimumSize(new Dimension(600, 620));
        setLocationRelativeTo(null); // Center window on screen
        setResizable(true);
    }

    
    private void connectActions() {

        // New Game
        gameMenuBar.getNewGameItem().addActionListener(e -> {
            gameState.resetGame();
            winnerDialogShown = false;
            refreshGameDisplay();
        });
        // Save Game
        gameMenuBar.getSaveGameItem().addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();

            if (chooser.showSaveDialog(this)
                    == JFileChooser.APPROVE_OPTION) {
                try {
                    GameStorage.saveGame(
                        gameState,
                        chooser.getSelectedFile()
                    );

                    JOptionPane.showMessageDialog(
                        this, "Game saved successfully!"
                    );

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Save failed: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Load Game
        gameMenuBar.getLoadGameItem().addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();

            if (chooser.showOpenDialog(this)
                    == JFileChooser.APPROVE_OPTION) {
                try {
                    gameState = GameStorage.loadGame(
                        chooser.getSelectedFile()
                    );

                    refreshGameDisplay();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Load failed: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Exit
        gameMenuBar.getExitItem().addActionListener(e -> {
            System.exit(0);
        });

        // Undo
        historyPanel.getUndoButtonPublic().addActionListener(e -> {
            if (gameState.undoLastMove() != null) {
                refreshGameDisplay();
            }
        });
    }

    /**
     * Refreshes the GUI using the current GameState.
     */
    public void refreshGameDisplay() {

        boardPanel.setBoard(gameState.getBoard());

        if (gameState.isGameOver() && !winnerDialogShown) {
            winnerDialogShown = true;
            showWinnerDialog(gameState.getWinner());
        }
    }

    /**
     * Returns the active GameState.
     */
    public GameState getGameState() {
        return gameState;
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
