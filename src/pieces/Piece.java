package pieces;

import board.Position;

// this class will be used to represent a piece on the board. it will have a color 
// and a position. it will also have a method to move the piece to a new position.
// this class will be abstract because we will have different types of pieces that will
// extend this class; basically being the blueprint for the other pieces

public abstract class Piece {
    private Color color;
    private Position currentPosition;

    public Piece(Color color, Position position){
        this.color = color;
        this.currentPosition = position;


    }   

    public Color getColor(){return this.color;}
    public Position getCurrentPosition(){return this.currentPosition;}

    public void move(Position newPosition){
        this.currentPosition = newPosition;
    }
}