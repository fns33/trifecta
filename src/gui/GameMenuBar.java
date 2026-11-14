/*
* Project Part 2
* Created by Member 2: jcb315
*/

package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class represents the menu bar in the chess game GUI.
 * 
 * It provides menu options for:
 * - New Game
 * - Save Game
 * - Load Game
 * - Exit
 * 
 */

public class GameMenuBar extends JMenuBar {

    private JMenu gameMenu;

    private JMenuItem newGameItem;
    private JMenuItem saveGameItem;
    private JMenuItem loadGameItem;
    private JMenuItem exitItem;

    public GameMenuBar() {
        gameMenu = new JMenu("Game");

        newGameItem = new JMenuItem("New Game");
        saveGameItem = new JMenuItem("Save Game");
        loadGameItem = new JMenuItem("Load Game");
        exitItem = new JMenuItem("Exit");

        gameMenu.add(newGameItem);
        gameMenu.add(saveGameItem);
        gameMenu.add(loadGameItem);
        gameMenu.add(exitItem);

        add(gameMenu);
    }

/**
 * Getter for the game menu.
 * 
 * @return The game menu.
 */

    public JMenu getGameMenu() {
        return gameMenu;
    }

/**
 * Getter for the "New Game" menu item.
 *  
 * @return The "New Game" menu item.
 */

    public JMenuItem getNewGameItem() {
        return newGameItem;
    }

/**
 * Getter for the "Save Game" menu item.
 *  
 * @return The "Save Game" menu item.
 */

    public JMenuItem getSaveGameItem() {
        return saveGameItem;
    }
/**
 * Getter for the "Load Game" menu item.
 *  
 * @return The "Load Game" menu item.
 */

    public JMenuItem getLoadGameItem() {
        return loadGameItem;
    }

/**
 * Getter for the "Exit" menu item.
 *  
 * @return The "Exit" menu item.
 */

    public JMenuItem getExitItem() {
        return exitItem;
    }

}    