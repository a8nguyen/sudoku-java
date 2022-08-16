package sudoku.model;

import java.util.ArrayList;

public class RowSection extends Section {
    public RowSection(ArrayList<ArrayList<Square>> puzzle) {
        super(puzzle);
        this.puzzle = puzzle;
        this.transform();
    }

    public void transform() {
        this.transformedPuzzle = this.puzzle;
    }
    

}
