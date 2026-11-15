/**
* Project Part 2
* Created by Member 2: jcb315
*/

package gui;

import javax.swing.*;
import java.awt.*;

import javax.swing.JPanel;

import game.MoveRecord;

public class GameHistoryPanel extends JPanel {

/**
 * This class represents the game history panel in the chess GUI.
 * It displays the history of moves made during the game 
 * and provides an undo button.
 */

    private JTextArea historyTextArea;
    private JButton undoButton;

    public GameHistoryPanel() {
        setLayout(new BorderLayout());

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        add(scrollPane, BorderLayout.CENTER);

        undoButton = new JButton("Undo");
        add(undoButton, BorderLayout.SOUTH);
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
}