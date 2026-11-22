
package gui;

import javax.swing.*;
import java.awt.*;
import game.GameState;
import game.GameStorage;
import java.io.File;
import game.MoveRecord;

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

        JPanel boardContainer = new JPanel(new BorderLayout());

        // Rank labels on the left side.
        JPanel rankLabels = new JPanel(new GridLayout(8, 1));

        for (int rank = 1; rank <= 8; rank++) {
            JLabel label = new JLabel(
                String.valueOf(rank),
                SwingConstants.CENTER
            );

            label.setPreferredSize(new Dimension(24, 0));
            rankLabels.add(label);
        }

        // File labels below the chessboard.
        JPanel fileLabels = new JPanel(new GridLayout(1, 8));

        for (char file = 'A'; file <= 'H'; file++) {
            JLabel label = new JLabel(
                String.valueOf(file),
                SwingConstants.CENTER
            );

            fileLabels.add(label);
        }

        // Preserve the existing BoardPanel and its mouse listeners.
        boardContainer.add(rankLabels, BorderLayout.WEST);

        JPanel bottomLabels = new JPanel(new BorderLayout());
        bottomLabels.add(
            Box.createHorizontalStrut(24),
            BorderLayout.WEST
        );
        bottomLabels.add(fileLabels, BorderLayout.CENTER);

        boardContainer.add(bottomLabels, BorderLayout.SOUTH);
        boardContainer.add(boardPanel, BorderLayout.CENTER);

        add(boardContainer, BorderLayout.CENTER);


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
    * Refreshes the board and move history using the current GameState.
    */
    public void refreshGameDisplay() {

        // Clear selections after moves, Undo, New Game, or Load Game.
        boardPanel.getMouseController().resetSelection();

        // Refresh the board.
        boardPanel.setBoard(gameState.getBoard());

        // Rebuild the move history from the current game state.
        historyPanel.clearHistory();

        for (MoveRecord move : gameState.getMoveHistory()) {
            historyPanel.addMove(move);
        }

        historyPanel.updateCapturedPieces(gameState.getCapturedPieces());

        // Notify the players if/when a checkmate occurs
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
            "👑 Checkmate!! " + winningColor
                + " wins the game!",
            "Winner Declared",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        // REQUIREMENT: Termination of the application after a King is captured.
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
