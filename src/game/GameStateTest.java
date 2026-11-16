
package game;

import board.Position;
import pieces.Color;
import pieces.Piece;

public class GameStateTest {

    public static void main(String[] args) {

        GameState game = new GameState();

        // TEST 1: Initial board
        System.out.println("TEST 1: Initial board");

        int count = 0;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (game.getBoard().getPiece(
                    new Position(r, c)) != null) {
                    count++;
                }
            }
        }

        check(count == 32, "Board contains 32 pieces");
        check(game.getCurrentTurn() == Color.WHITE,
              "White starts first");

        // TEST 2: Normal movement
        System.out.println("\nTEST 2: Normal movement");

        Position from = new Position(6, 4);
        Position to = new Position(4, 4);

        check(game.executeMove(from, to),
              "White pawn moves two squares");

        check(game.getBoard().getPiece(from) == null,
              "Original square is empty");

        check(game.getBoard().getPiece(to) != null,
              "Destination contains pawn");

        check(game.getCurrentTurn() == Color.BLACK,
              "Turn switches to Black");

        // TEST 3: Prevent wrong player movement
        System.out.println("\nTEST 3: Turn validation");

        check(!game.executeMove(
            new Position(6, 3),
            new Position(5, 3)),
            "White cannot move twice");

        // TEST 4: Undo
        System.out.println("\nTEST 4: Undo");

        check(game.undoLastMove() != null,
              "Undo returns a MoveRecord");

        check(game.getBoard().getPiece(from) != null,
              "Pawn restored to original square");

        check(game.getBoard().getPiece(to) == null,
              "Destination is empty");

        check(game.getCurrentTurn() == Color.WHITE,
              "Turn restored to White");

        System.out.println("\nAll basic tests completed!");

        
        // TEST 5: Capturing an opponent's piece
        System.out.println("\nTEST 5: Capturing");

        // Reset the game for a predictable starting position
        game.resetGame();

        // White pawn moves forward
        check(game.executeMove(
            new Position(6, 4),
            new Position(4, 4)),
            "White pawn moves");

        // Black pawn moves forward
        check(game.executeMove(
            new Position(1, 3),
            new Position(3, 3)),
            "Black pawn moves");

        // White pawn captures Black pawn
        check(game.executeMove(
            new Position(4, 4),
            new Position(3, 3)),
            "White captures Black pawn");

        check(game.getBoard().getPiece(
            new Position(3, 3)).getColor() == Color.WHITE,
            "White pawn occupies captured square");

        check(game.getCapturedPieces().size() == 1,
            "Captured piece recorded");

        check(game.getMoveHistory().size() == 3,
            "Three moves recorded");

        // TEST 6: Undo capture
        System.out.println("\nTEST 6: Undo capture");

        game.undoLastMove();

        check(game.getCapturedPieces().isEmpty(),
            "Captured pieces list restored");

        check(game.getBoard().getPiece(
            new Position(3, 3)).getColor() == Color.BLACK,
            "Black pawn restored");

        check(game.getBoard().getPiece(
            new Position(4, 4)).getColor() == Color.WHITE,
            "White pawn returned to original square");

            
        // TEST 7: King capture and winner detection
        System.out.println("\nTEST 7: King capture");

        game.resetGame();

        // Move White's e-pawn forward
        game.executeMove(
            new Position(6, 4),
            new Position(4, 4));

        // Move Black's e-pawn forward
        game.executeMove(
            new Position(1, 4),
            new Position(3, 4));

        // Move White's e-pawn again
        game.executeMove(
            new Position(4, 4),
            new Position(3, 4));

        // Move Black's King into a capturable square
        game.executeMove(
            new Position(0, 4),
            new Position(4, 4));

        // White captures Black's King
        check(game.executeMove(
            new Position(3, 4),
            new Position(4, 4)),
            "White captures Black King");

        check(game.isGameOver(),
            "Game-over state activated");

        check("White".equals(game.getWinner()),
            "White declared winner");

        check(game.getCapturedPieces().get(
            game.getCapturedPieces().size() - 1)
            instanceof pieces.King,
            "Captured piece is a King");

        // TEST 8: Prevent moves after game over
        System.out.println("\nTEST 8: Game-over protection");

        check(!game.executeMove(
            new Position(7, 1),
            new Position(5, 2)),
            "Moves prevented after King capture");

        // TEST 9: Undo King capture
        System.out.println("\nTEST 9: Undo King capture");

        game.undoLastMove();

        check(!game.isGameOver(),
            "Game-over state cleared");

        check(game.getWinner() == null,
            "Winner cleared");

        check(game.getBoard().getPiece(
            new Position(4, 4)) instanceof pieces.King,
            "Black King restored");

        check(game.getCurrentTurn() == Color.WHITE,
            "White's turn restored");


    }

    private static void check(boolean condition, String description) {
        if (condition) {
            System.out.println("[PASS] " + description);
        } else {
            System.out.println("[FAIL] " + description);
        }
    }
}
