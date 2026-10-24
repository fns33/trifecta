package game;

import board.Board;


// Main driver to test Board init. and CLI display for Milestone 1
public class Main {
    public static void main(String[] args){
        System.out.println("=== Console Chess Game (Phase 1) ===");
        System.out.println("Initializing Board...\n");

        Board board = new Board();
        board.display();

        System.out.println("\nInitial Board Setup Complete!");
    }
}