import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import sudoku.model.*;

public class BoardTest {


    @Test
    public void testGetInvalidsReturnListOfSquare(){
        ArrayList<ArrayList<Square>> Puzzle = new ArrayList<ArrayList<Square>>();
        /*creating an
        1 2 3 4
        1 2 3 4
        1 2 3 4
        1 2 3 4*/
        for (int row = 0; row < 4; row++) {
            ArrayList<Square> row_vec = new ArrayList<Square>();
            for (int i = 0; i < 4; i++) {
                row_vec.add(new Square(row, i, i + 1));
            }
            Puzzle.add(row_vec);
        }
        Board board = new Board(Puzzle);
        HashSet<Square> invalSquare = board.getInvalidSquares();
        HashSet<Square> allSquareHash = new HashSet<Square>();
        board.getAllSquares().forEach(sq -> {allSquareHash.add(sq);});
        assertEquals("testing invalSquares returns everything", invalSquare, allSquareHash);


    }
    @Test
    public void testGetInvalidsReturnNull(){
        ArrayList<ArrayList<Square>> Puzzle = new ArrayList<ArrayList<Square>>();
        for (int row = 0; row < 1; row++) {
            ArrayList<Square> row_vec = new ArrayList<Square>();
            for (int i = 0; i < 1; i++) {
                row_vec.add(new Square(row, i, i + 1));
            }
            Puzzle.add(row_vec);
        }
        Board board = new Board(Puzzle);
        HashSet<Square> invalSquare = board.getInvalidSquares();

        assertEquals("testing invalSquares returns empty set", invalSquare.size(), 0);


    }
    @Test
    public void testGetIsCompleteReturnsTrue() {
        ArrayList<ArrayList<Square>> Puzzle = new ArrayList<ArrayList<Square>>();
        for (int row = 0; row < 1; row++) {
            ArrayList<Square> row_vec = new ArrayList<Square>();
            for (int i = 0; i < 1; i++) {
                row_vec.add(new Square(row, i, i +1));
            }
            Puzzle.add(row_vec);
        }
        Board board = new Board(Puzzle);

        assertEquals("testing invalSquares", board.isComplete(), true);

    }
    @Test
    public void testGetIsCompleteReturnsFalse() {

        ArrayList<ArrayList<Square>> Puzzle = new ArrayList<ArrayList<Square>>();
        for (int row = 0; row < 1; row++) {
            ArrayList<Square> row_vec = new ArrayList<Square>();
            for (int i = 0; i < 1; i++) {
                row_vec.add(new Square(row, i, i));
            }
            Puzzle.add(row_vec);
        }
        Board board = new Board(Puzzle);

        assertEquals("testing invalSquares", board.isComplete(), false);

    }
}
