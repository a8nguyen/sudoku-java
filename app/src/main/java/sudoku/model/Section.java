package sudoku.model;

import java.util.ArrayList;
import java.util.HashSet;


public abstract class Section{
    protected ArrayList<ArrayList<Square>> puzzle;
    protected ArrayList<ArrayList<Square>> transformedPuzzle = new ArrayList<ArrayList<Square>>();
    protected int size_squared;

    public Section(ArrayList<ArrayList<Square>> puzzle){
        this.puzzle = puzzle;
        this.size_squared = this.puzzle.size();
        //this.transform();
    }
    
    
    public abstract void transform();
    public ArrayList<ArrayList<Square>> getTransform(){
        return this.transformedPuzzle;
    }

    public HashSet<Square> getInvalids(){
        HashSet<Square> invalSet = new HashSet<Square> ();
        for (ArrayList<Square> sqList : this.transformedPuzzle) {
            for(int i = 0; i < this.size_squared; i ++){
                if (sqList.get(i).getValue() == 0) {
                        continue;
                }
                else{
                    for(int j = i+1; j < this.size_squared; j++){
                        if(sqList.get(i).equals(sqList.get(j))){
                            invalSet.add(sqList.get(i));
                            invalSet.add(sqList.get(j));
                        }
                    }
                    
                }

                }
            }
            return invalSet;
        }
        
    public ArrayList<Square> getByIndex(int idx){
        return this.transformedPuzzle.get(idx);
    }
    

    public HashSet<Integer> getUniqueValueByIndex(int idx){
        HashSet<Integer> uniqueVals = new HashSet<Integer> ();
        for(Square sq: this.transformedPuzzle.get(idx)){
            uniqueVals.add(sq.getValue());
        }
        return uniqueVals;
    }



}
