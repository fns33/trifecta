package board;
import pieces.Piece;
import pieces.Color;
import pieces.Pawn;
import pieces.Rook;
import pieces.Knight;
import pieces.Bishop;
import pieces.Queen;
import pieces.King;

// This class represents the 8x8 chessboard matrix and handles board setup and CLI display rendering.

public class Board { 
    private Piece[][] grid; // 8x8 matrix to store piece objects

// Constructor creates an 8x8 grid and populates all 32 starting chess pieces 
    public Board() {
        grid = new Piece[8][8];
        initializeBoard();
    }
    
    // Populates the 8x8 matrix with pieces in standard chess starting positions
    public void initializeBoard() {
        // Clear all board squares
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                grid[r][c] = null; 
            }
        }
        // Row 0 (Rank 8): Black major pieces
        grid[0][0] = new Rook(Color.BLACK, new Position(0, 0));
        grid[0][1] = new Knight(Color.BLACK, new Position(0, 1));
        grid[0][2] = new Bishop(Color.BLACK, new Position(0, 2));
        grid[0][3] = new Queen(Color.BLACK, new Position(0, 3)); 
        grid[0][4] = new King(Color.BLACK, new Position(0, 4));
        grid[0][5] = new Bishop(Color.BLACK, new Position(0, 5));
        grid[0][6] = new Knight(Color.BLACK, new Position(0, 6));
        grid[0][7] = new Rook(Color.BLACK, new Position(0, 7));
        
        // Row 1 (Rank 7): Black Pawns
        for (int c = 0; c < 8; c++) {
            grid[1][c] = new Pawn(Color.BLACK, new Position(1, c));
        } 

        // Row 6 (Rank 2): White Pawns
        for (int c = 0; c < 8; c++) {
            grid[6][c] = new Pawn(Color.WHITE, new Position(6, c));
        } 
        
        // Row 7 (Rank 1): White major pieces
        grid[7][0] = new Rook(Color.WHITE, new Position(7, 0));
        grid[7][1] = new Knight(Color.WHITE, new Position(7, 1));
        grid[7][2] = new Bishop(Color.WHITE, new Position(7, 2));
        grid[7][3] = new Queen(Color.WHITE, new Position(7, 3));
        grid[7][4] = new King(Color.WHITE, new Position(7, 4));
        grid[7][5] = new Bishop(Color.WHITE, new Position(7, 5));
        grid[7][6] = new Knight(Color.WHITE, new Position(7, 6));
        grid[7][7] = new Rook(Color.WHITE, new Position(7, 7)); 
    }

    // Allows getting the piece at a given row and column position 
    public Piece getPiece(Position pos) {
        if (pos == null) return null;
        return grid[pos.getRow()][pos.getCol()];
    }
    
    // Places or updates a piece at a specified position on the grid
    public void setPiece(Piece piece, Position pos) {
        if (pos != null) {
            grid[pos.getRow()][pos.getCol()] = piece;
            if (piece != null) {
                piece.move(pos);
            }
        }
    }
    
    // Renders the board state to the console with file labels (A-H), rank numbers (1-8), and dark squares (##)
    public void display() {
        System.out.println("   A B C D E F G H");

        for (int r = 0; r < 8; r++) {
            int rankNumber = 8 - r; // Maps array row 0 to Rank 8 and row 7 to Rank 1
            System.out.print(rankNumber + " ");
            
            for (int c = 0; c < 8; c++) {
                Piece piece = grid[r][c];
                if (piece != null) {
                    System.out.print(piece.getSymbol() + " ");
                } else {
                    // Alternates dark square (##) and light square (space) pattern
                    if ((r + c) % 2 != 0) {
                        System.out.print("## ");
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
        }
    }
}