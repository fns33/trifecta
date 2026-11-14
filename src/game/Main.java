package game; 

import board.Board; 
import board.Position; 
import pieces.Piece; 
import java.util.List; 

public class Main { 

    public static void main(String[] args) { 
        System.out.println("=== Console Chess Game (Phase 1 & M3 Movement Test) ===");
        System.out.println("Initializing Board...\n");

        Board board = new Board();
        board.display();

        System.out.println("\n--------------------------------------------------"); 
        System.out.println("--- STAGE 1: Testing Starting Piece Moves ---");
        System.out.println("--------------------------------------------------"); 

        // Test 1: White Pawn at E2 (Row 6, Col 4) -> Should offer E3 and E4
        testPieceMoves(board, 6, 4, "White Pawn at E2"); 
        // Test 2: Black Pawn at E7 (Row 1, Col 4) -> Should offer E6 and E5
        testPieceMoves(board, 1, 4, "Black Pawn at E7"); 
        // Test 3: White Knight at B1 (Row 7, Col 1) -> Should offer A3 and C3
        testPieceMoves(board, 7, 1, "White Knight at B1"); 
        // Test 4: White Bishop at C1 (Row 7, Col 2) -> Should be blocked initially
        testPieceMoves(board, 7, 2, "White Bishop at C1 (Blocked)"); 
        System.out.println("\n--------------------------------------------------"); 
        System.out.println("--- STAGE 2: Opening Diagonal Path for Bishop ---");
        System.out.println("--------------------------------------------------"); 

        // Move White Pawn from D2 (Row 6, Col 3) to D4 (Row 4, Col 3)
        Position d2 = new Position(6, 3);
        Position d4 = new Position(4, 3); 
        Piece pawn = board.getPiece(d2);

        if (pawn != null) {
            board.setPiece(pawn, d4);
            board.setPiece(null, d2);
            pawn.move(d4); 
            System.out.println("Action: Moved White Pawn D2 -> D4 to clear diagonal line-of-sight!\n");
        } 
        board.display(); 

        // Test 5: Re-test White Bishop at C1 (Row 7, Col 2) -> Should now list D2, E3, F4, G5, H6
            testPieceMoves(board, 7, 2, "White Bishop at C1 (Unblocked)"); 

        // Test 6: Re-test White Queen at D1 (Row 7, Col 3) -> Should now list D2 and D3 
            testPieceMoves(board, 7, 3, "White Queen at D1 (Unblocked)"); 

        System.out.println("\n=== All Milestone 3 Piece Movement Tests Complete! ==="); 
    } 

    private static void testPieceMoves(Board board, int row, int col, String label) {
        Position pos = new Position(row, col); 
        Piece piece = board.getPiece(pos); 

        if (piece == null) { 
            System.out.println("\n[" + label + "] No piece found at position!"); return; 
        } 

        System.out.println("\nTesting: " + label + " (" + piece.getSymbol() + ")"); 
        List<Position> moves = piece.possibleMoves(board);

        if (moves.isEmpty()) {
            System.out.println("  -> Possible moves: None (Piece is blocked by friendly pieces)");
        } 
        else {
            System.out.print("  -> Possible moves (" + moves.size() + "): ");
            for (Position p : moves) {
                char file = (char) ('A' + p.getCol());
                int rank = 8 - p.getRow();
                System.out.print(file + "" + rank + " ");
            }

            System.out.println();
        }
    }
}

