
package game;

import board.Board;
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

            

        // TEST 7: Reject King captures
        System.out.println("\nTEST 7: King capture prevention");

        game.resetGame();

        // Set up a position where a White rook can attack
        // the Black King.
        Board board = game.getBoard();

        // Clear all squares.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.setPiece(null, new Position(row, col));
            }
        }

        board.setPiece(
            new pieces.King(Color.WHITE, new Position(7, 0)),
            new Position(7, 0)
        );

        board.setPiece(
            new pieces.King(Color.BLACK, new Position(0, 4)),
            new Position(0, 4)
        );

        board.setPiece(
            new pieces.Rook(Color.WHITE, new Position(1, 4)),
            new Position(1, 4)
        );

        check(!game.executeMove(
            new Position(1, 4),
            new Position(0, 4)),
            "King capture rejected");

        check(game.getBoard().getPiece(
            new Position(0, 4)) instanceof pieces.King,
            "Black King remains on board");

        check(game.getMoveHistory().isEmpty(),
            "Rejected capture not recorded");

        check(game.getCurrentTurn() == Color.WHITE,
            "Turn unchanged after rejected capture");


        // TEST 8: Checkmate and game-over protection
        System.out.println("\nTEST 8: Checkmate detection");

        game.resetGame();

        // Fool's Mate: 1. f3 e5 2. g4 Qh4#
        boolean move1 = game.executeMove(
            new Position(6, 5), new Position(5, 5));

        boolean move2 = game.executeMove(
            new Position(1, 4), new Position(3, 4));

        boolean move3 = game.executeMove(
            new Position(6, 6), new Position(4, 6));

        boolean move4 = game.executeMove(
            new Position(0, 3), new Position(4, 7));

        check(move1 && move2 && move3 && move4,
            "Fool's Mate moves accepted");

        check(game.isGameOver(),
            "Checkmate ends the game");

        check("Black".equals(game.getWinner()),
            "Black declared winner");

        check(game.getMoveHistory().size() == 4,
            "Four moves recorded");

        check(!game.executeMove(
            new Position(1, 0),
            new Position(2, 0)),
            "Further moves rejected after checkmate");

        check(game.getMoveHistory().size() == 4,
            "Rejected move not recorded");


        // TEST 9: Undo checkmate
        System.out.println("\nTEST 9: Undo checkmate");

        MoveRecord undoneMove = game.undoLastMove();

        check(undoneMove != null,
            "Checkmating move undone");

        check(!game.isGameOver(),
            "Game-over state cleared");

        check(game.getWinner() == null,
            "Winner cleared");

        check(game.getCurrentTurn() == Color.BLACK,
            "Black's turn restored");

        check(game.getMoveHistory().size() == 3,
            "Move history restored");

        check(game.getBoard().getPiece(
            new Position(0, 3)) instanceof pieces.Queen,
            "Black Queen restored to D8");

        check(game.getBoard().getPiece(
            new Position(4, 7)) == null,
            "H4 is empty after Undo");


    }

    private static void check(boolean condition, String description) {
        if (condition) {
            System.out.println("[PASS] " + description);
        } else {
            System.out.println("[FAIL] " + description);
        }
    }
}
