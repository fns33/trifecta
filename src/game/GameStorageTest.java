package game;

import board.Position;
import pieces.Color;
import pieces.Piece;

import java.io.File;

public class GameStorageTest {

    public static void main(String[] args) throws Exception {

        File saveFile = new File("test-save.dat");

        System.out.println("TEST 1: Save game");

        GameState original = new GameState();

        original.executeMove(
            new Position(6, 4),
            new Position(4, 4)
        );

        GameStorage.saveGame(original, saveFile);

        System.out.println("[PASS] Game saved successfully");

        System.out.println("\nTEST 2: Load game");

        GameState loaded = GameStorage.loadGame(saveFile);

        System.out.println("[PASS] Game loaded successfully");

        System.out.println("\nTEST 3: Verify restored state");

        Piece restoredPawn = loaded.getBoard().getPiece(
            new Position(4, 4)
        );

        if (restoredPawn != null &&
            restoredPawn.getColor() == Color.WHITE) {
            System.out.println("[PASS] Pawn position restored");
        } else {
            System.out.println("[FAIL] Pawn position incorrect");
        }

        if (loaded.getCurrentTurn() == Color.BLACK) {
            System.out.println("[PASS] Current turn restored");
        } else {
            System.out.println("[FAIL] Current turn incorrect");
        }

        if (loaded.getMoveHistory().size() == 1) {
            System.out.println("[PASS] Move history restored");
        } else {
            System.out.println("[FAIL] Move history incorrect");
        }

        System.out.println("\nTEST 4: Cleanup");

        if (saveFile.delete()) {
            System.out.println("[PASS] Test save file deleted");
        }

        System.out.println("\nAll GameStorage tests completed!");
    }
}