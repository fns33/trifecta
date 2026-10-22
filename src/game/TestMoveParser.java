import board.Position;
import game.MoveParser;

public class TestMoveParser {

    public static void main(String[] args) {

        Position p = MoveParser.parsePosition("E2");

        System.out.println(p.toChessNotation());

        System.out.println(
            MoveParser.isValidSquare("A1")
        );

        System.out.println(
            MoveParser.isValidSquare("H8")
        );

        System.out.println(
            MoveParser.isValidSquare("Z9")
        );
    }
}