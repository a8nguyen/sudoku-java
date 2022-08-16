package sudoku.model;
import java.util.ArrayList;

public class ColumnSection extends Section{
    public ColumnSection(ArrayList<ArrayList<Square>> puzzle){
        super(puzzle);
        this.puzzle = puzzle;
        this.transform();
    }
    public void transform(){
        ArrayList<Square> colValues;
        
        for(int j=0; j< this.size_squared; j++){
            colValues= new ArrayList<Square> (size_squared);
            for(int i = 0; i < this.size_squared; i++){
                colValues.add(this.puzzle.get(i).get(j));
            }
            this.transformedPuzzle.add(colValues);
        }

    }

}
