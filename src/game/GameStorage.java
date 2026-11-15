package game;

import java.io.*;

/**
 * Handles file serialization (Save Game / Load Game)
 * for GameState.
 */
public class GameStorage {

    /**
     * Saves the current game state to a file.
     *
     * @param state GameState to save
     * @param file Destination file
     * @throws IOException if saving fails
     */
    public static void saveGame(GameState state, File file)
            throws IOException {

        if (state == null || file == null) {
            throw new IllegalArgumentException(
                "Game state and file cannot be null."
            );
        }

        try (ObjectOutputStream oos =
                new ObjectOutputStream(
                    new FileOutputStream(file))) {

            oos.writeObject(state);
        }
    }

    /**
     * Loads a previously saved game state.
     *
     * @param file File containing the saved game
     * @return Restored GameState
     * @throws IOException if loading fails
     * @throws ClassNotFoundException if a required class is missing
     */
    public static GameState loadGame(File file)
            throws IOException, ClassNotFoundException {

        if (file == null) {
            throw new IllegalArgumentException(
                "File cannot be null."
            );
        }

        try (ObjectInputStream ois =
                new ObjectInputStream(
                    new FileInputStream(file))) {

            return (GameState) ois.readObject();
        }
    }
}
