/*
M2 - T2 continued
Created by Member: jcb315
*/
package game;

import board.Position;

/* 
* Chess piece moves from starting position to desination
* Store and transfer move info
* 
*/

public class Move {

    private Position from;
    private Position to;

    /*
    * Create a move from starting position to destination
    * @param from starting position
    * @param to destination 
    */

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    /*
    * Return starting position
    * return the starting poition
    */

    public position getFrom() {
        return from;
    }

    /*
    * Return destination
    * @return destination
    */
   
    public Position getTo() {
        return to;
    }
}