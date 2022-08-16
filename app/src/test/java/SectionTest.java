import org.junit.BeforeClass;
import org.junit.*;

import static org.junit.Assert.*;
import java.util.ArrayList;
import sudoku.model.*;

public class SectionTest {
    private static ArrayList<ArrayList<Square>> Puzzle;
    

    @BeforeClass
    public static void setUp() {
        Puzzle = new ArrayList<ArrayList<Square>> ();
        for(int row = 0; row < 9; row ++){
            ArrayList<Square>  row_vec = new ArrayList<Square> ();
            for (int i = 0; i <  9; i++){
                row_vec.add(new Square(row, i, i+1));
            }
            Puzzle.add(row_vec);
        }
    }


    @Test
    public void testTransformBoxSection() {
        BoxSection transformed_puzzle = new BoxSection(Puzzle);
        transformed_puzzle.transform();
        Square sq_implemented = transformed_puzzle.getTransform().get(5).get(2);
        Square sq_manual = Puzzle.get(3).get(8);

        assertEquals("testing manually transpose ane implemented transpose", sq_implemented, sq_manual);
    }
    
    @Test
    public void testTransformColumnSection() {
        ColumnSection transformed_puzzle = new ColumnSection(Puzzle);
        transformed_puzzle.transform();
        Square sq_implemented = transformed_puzzle.getTransform().get(2).get(5);
        Square sq_manual = Puzzle.get(5).get(2);

        assertEquals("testing manuaally transpose ane implemented transpose", sq_implemented, sq_manual);
    }
}
