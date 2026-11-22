package game;

import board.Board;
import board.Position;
import pieces.Color;
import pieces.Piece;
import pieces.King;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Central game state manager for turn handling, moves,
 * history, and endgame checks.
 *
 * Integrates with MoveRecord.
 *
 * @author fns33
 */
public class GameState implements Serializable {

    private static final long serialVersionUID = 1L;

    private Board board;
    private Color currentTurn;
    private final List<MoveRecord> moveHistory;
    private final List<Piece> capturedPieces;
    private boolean gameOver;
    private String winner;

    public GameState() {
        this.moveHistory = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
        resetGame();
    }

    /**
     * Resets the board and game state.
     */
    public void resetGame() {
        this.board = new Board();
        this.currentTurn = Color.WHITE;
        this.moveHistory.clear();
        this.capturedPieces.clear();
        this.gameOver = false;
        this.winner = null;
    }

    /**
     * Executes a move and records it using MoveRecord.
     *
     * @param from Starting position
     * @param to Destination position
     * @return true if the move was executed successfully
     */
    public boolean executeMove(Position from, Position to) {

        if (gameOver || from == null || to == null) {
            return false;
        }

        Piece movingPiece = board.getPiece(from);

        // Ensure there is a piece at the starting position
        if (movingPiece == null) {
            return false;
        }

        // Ensure the piece belongs to the current player
        if (movingPiece.getColor() != currentTurn) {
            return false;
        }

        //phase 3: implementation: Restrict to only valid moves for the piece
        if (!movingPiece.possibleMoves(board).contains(to)){
            return false;
        }

        //phase 3: implementation: Check if the move would leave the King in check
        if (ChessRules.wouldLeaveKingInCheck(board, from, to, currentTurn)){
            return false;
        }

        // Get the destination piece
        Piece targetPiece = board.getPiece(to);

        // phase 3: implementation: Prevent captuing of King pieces
        // making victory be determined via checkmate
        if (targetPiece instanceof King) {
            return false;
        }

        // Prevent capturing your own pieces
        if (targetPiece != null &&
            targetPiece.getColor() == currentTurn) {
            return false;
        }

        // Record captured position
        Position capturedPos = (targetPiece != null) ? to : null;

        // Record move
        MoveRecord record = new MoveRecord(
            movingPiece,
            from,
            to,
            targetPiece,
            capturedPos
        );

        moveHistory.add(record);

        // Handle captures
        if (targetPiece != null) { capturedPieces.add(targetPiece); }

        board.setPiece(movingPiece, to);
        board.setPiece(null, from);

        Color opponentColor = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        if (ChessRules.isCheckmate(board, opponentColor)){
            gameOver = true;
            winner = (currentTurn == Color.WHITE) ? "White" : "Black";
        }

        // Switch turns
        if (!gameOver) {  switchTurn();  }

        return true;
    }

    /**
     * Reverts the last move using MoveRecord.
     *
     * @return The undone MoveRecord, or null if history is empty
     */
    public MoveRecord undoLastMove() {

        if (moveHistory.isEmpty()) {
            return null;
        }

        //Phase 3: Implementation: Store the current game-over state before undoing
        boolean wasGameOver = gameOver;

        MoveRecord lastMove =
            moveHistory.remove(moveHistory.size() - 1);

        Position startPos = lastMove.getStartPosition();
        Position endPos = lastMove.getEndPosition();

        Piece movedPiece = lastMove.getMovedPiece();
        Piece capturedPiece = lastMove.getCapturedPiece();

        // Restore the moved piece to its original position
        board.setPiece(movedPiece, startPos);

        // Restore the captured piece or clear the destination
        board.setPiece(capturedPiece, endPos);

        // Remove restored piece from captured pieces list
        if (capturedPiece != null) {
            capturedPieces.remove(capturedPiece);
        }

        // Restore game-over state
        gameOver = false;
        winner = null;

        if (!wasGameOver) { switchTurn(); }

        return lastMove;

    }

    /**
     * Switches the current player's turn.
     */
    public void switchTurn() {
        this.currentTurn =
            (this.currentTurn == Color.WHITE)
                ? Color.BLACK
                : Color.WHITE;
    }

    /**
     * Returns the current board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the current player's turn.
     */
    public Color getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Returns a copy of the move history.
     */
    public List<MoveRecord> getMoveHistory() {
        return new ArrayList<>(moveHistory);
    }

    /**
     * Returns a copy of the captured pieces list.
     */
    public List<Piece> getCapturedPieces() {
        return new ArrayList<>(capturedPieces);
    }

    /**
     * Checks whether the game has ended.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns the winner.
     */
    public String getWinner() {
        return winner;
    }
}
