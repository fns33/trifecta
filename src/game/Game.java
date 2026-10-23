/*
M2 - T1 Continued
M2 - T2
m2 - T3
Created by Member 2: jcb315
*/

package game;

import java.util.Scanner;

import board.Position;
import pieces.Color;

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