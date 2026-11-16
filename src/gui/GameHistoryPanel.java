/**
* Project Part 2
* Created by Member 2: jcb315
*/

package gui;

import javax.swing.*;
import java.awt.*;

import game.MoveRecord;
import pieces.Piece;
import pieces.Color;    
import java.util.List;


public class GameHistoryPanel extends JPanel {

/**
 * This class represents the game history panel in the chess GUI.
 * It displays the history of moves made during the game 
 * and provides an undo button.
 */

    private JTextArea historyTextArea;
    private JButton undoButton;
    private JTextArea capturedTextArea;

    public GameHistoryPanel() {
        setLayout(new BorderLayout());

        capturedTextArea = new JTextArea(4, 18);
        capturedTextArea.setEditable(false);
        capturedTextArea.setLineWrap(true);
        capturedTextArea.setWrapStyleWord(true);

        JScrollPane capturedScroll = new JScrollPane(capturedTextArea);
        
        capturedScroll.setBorder(
            BorderFactory.createTitledBorder("Captured Pieces")
        );

        capturedScroll.setPreferredSize(
            new Dimension(230, 140)
        );

        add(capturedScroll, BorderLayout.NORTH);

        updateCapturedPieces(java.util.Collections.emptyList());


        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setLineWrap(true);
        historyTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        add(scrollPane, BorderLayout.CENTER);

        undoButton = new JButton("Undo");
        add(undoButton, BorderLayout.SOUTH);
    }

    /**
     * Updates the displayed pieces captured by each player.
     *
     * @param capturedPieces pieces removed from the board
     */
    public void updateCapturedPieces(List<Piece> capturedPieces) {

        StringBuilder whiteCaptured =
            new StringBuilder("White Captured: ");

        StringBuilder blackCaptured =
            new StringBuilder("Black Captured: ");

        int whiteCount = 0;
        int blackCount = 0;

        for (Piece piece : capturedPieces) {

            if (piece.getColor() == Color.BLACK) {

                // White captured a Black piece.
                if (whiteCount++ > 0) {
                    whiteCaptured.append(", ");
                }

                whiteCaptured.append(piece.getSymbol());

            } else {

                // Black captured a White piece.
                if (blackCount++ > 0) {
                    blackCaptured.append(", ");
                }

                blackCaptured.append(piece.getSymbol());
            }
        }

        if (whiteCount == 0) {
            whiteCaptured.append("None");
        }

        if (blackCount == 0) {
            blackCaptured.append("None");
        }

        capturedTextArea.setText(
            whiteCaptured + "\n\n" + blackCaptured
        );
    }


    private JTextArea getHistoryTextArea() {
        return historyTextArea;
    }

    public JButton getUndoButtonPublic() {
        return undoButton;
    }

    public void addMove(MoveRecord move) {

        String moveText = String.format("%s from %s to %s", 
            move.getMovedPiece().getSymbol(), 
            move.getStartPosition().toChessNotation(), 
            move.getEndPosition().toChessNotation());

        if (move.getCapturedPiece() != null) {

            moveText += String.format(", captured %s at %s", 
                move.getCapturedPiece().getSymbol(), 
                move.getCapturedPosition().toChessNotation());
        }
        
        historyTextArea.append(moveText + "\n");
    }

    // Clears old history when a new game is started
    // AUTHOR: lcx2
    public void clearHistory() {
        historyTextArea.setText("");
    }

}