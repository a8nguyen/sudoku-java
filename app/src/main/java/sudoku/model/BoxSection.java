package sudoku.model;
import java.util.ArrayList;
import static java.lang.Math.sqrt;
import java.util.HashSet;

public class BoxSection extends Section {
    
    public BoxSection(ArrayList<ArrayList<Square>> puzzle){
        super(puzzle);
        this.puzzle = puzzle;
        this.transform();
    }

    public void transform(){
        int size = (int) sqrt(this.size_squared);

        for(int i = 0; i<size; ++i){
            for(int j = 0; j < size; ++j){
                ArrayList<Square> bufferList = new ArrayList<Square>(this.size_squared);
                for(int locali = 0; locali < size; ++locali){
                    for(int localj = 0; localj <size; ++localj){
                        bufferList.add(this.puzzle.get(size*i + locali).get(size*j+localj));
                    }
                }
                this.transformedPuzzle.add(bufferList);
            }
        }
        
        
    }

    
    public ArrayList<Square> getByIndex(int row, int col){
        int size = (int) sqrt(size_squared);
        int bin = row/size;
        int offset = bin*size;
        int realIndex = offset + col/size; 
        return super.getByIndex(realIndex);
    }
    
    public HashSet<Integer> getUniqueValueByIndex(int row, int col){
        int size = (int) sqrt(size_squared);
        return super.getUniqueValueByIndex((row/size) + (col/size));
    }
}
