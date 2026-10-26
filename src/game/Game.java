/*
M2 - T1 Continued
M2 - T2
M2 - T3
M2 - T4 in progress
Created by Member 2: jcb315
*/

package game;

import java.util.Scanner;

import board.Board;
import board.Position;
import pieces.Color;

/*
* Parses a move in the format:
* [FROM] [TO]
*
* @param input move entered by user
* @return array containing ource and destination positions
*/

public class Game {

    private Color currentTurn;

    public Game() {
        currentTurn = Color.WHITE;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    /*
    * Switches the turn from WHITE to BLACK or
    * witches the turn from BLACK to WHITE
    */
   
    public void switchTurn() {

        if (currentTurn == Color.WHITE) {
            currentTurn = Color.BLACK;        
        }

        else {
            currentTurn = Color.WHITE;
        }
    }

    // game loop    
    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print(currentTurn + " 's turn.' Enter move: ");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {

                System.out.println(
                    "Thanks for playing!"
                );
                
                break;
            }

            try {

                Position[] move = 
                    MoveParser.parseMove(input);

                System.out.println(
                    "Move accepted." 
                );    

                System.out.println(
                    "From: " +
                    move[0].toChessNotation()
                );   
        
                System.out.println(
                    "To: " +
                    move[1].toChessNotation()
                );
        
                switchTurn();

            }

            catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());

            }
        }        
    }      
}