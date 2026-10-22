
package board;

public class Position {
    private int row;              //x-axis of the position 
    private int col;              //y-axis of the position




//This would be the constructor for the Position class
//by taking the x and y coordinates as parameters and
//checking if they are valid positions on the board. 
// If they are not valid, it throws an IllegalArgumentException.

public Position(int row, int col){

    if  (!isValid(row, col)){
        throw new IllegalArgumentException("Invalid position: (" + row + ", " + col + ")");
    }

    this.row = row;
    this.col = col;

}

public static boolean isValid(int row, int col) {
    
    return row >= 0 && row < 8 && col >= 0 && col < 8;
}


//Allow for getting the row and column values of the position. 
public int getRow() { return row;}
public int getCol() { return col;}

//This will create the the notation for the position in the form of a string.
//with column to file (A-H) and row to rank (1-8).

public String toChessNotation() {
    char file = (char) ('A' + col); 
    int rank = row + 1; 

    return "" + file + rank;
}

}