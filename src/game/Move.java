/*
M2 - T2 continued
Created by Member: jcb315
*/
package game;

import board.Position;

public class Move {

    private Position from;
    private Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}