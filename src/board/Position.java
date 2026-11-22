
package board;

import java.util.Objects;
import java.io.Serializable;

public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

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
    int rank = 8 - row;

    return "" + file + rank;
}

/**
 * Checks two positions by their board coordinates
 *
 * @param obj the object to compare against this position
 * @return true if the positions are equal
 */
@Override
public boolean equals(Object obj){
    if (this == obj){  return true;  }
    if (!(obj instanceof Position)){  return false;  }

    Position other = (Position) obj;

    return this.row == other.row && this.col == other.col;
}

/* Hash code coordinates
 * @returns the hash code for this position
*/
@Override
public int hashCode() {
    return Objects.hash(row,col);
}
}