package sudoku.model;

import java.util.stream.*;
import java.util.ArrayList;
import java.util.Collections;

public class BoardSolver{
    protected ArrayList<ArrayList<Integer>> answer;
    protected int size;
    protected int size_squared;
    protected ArrayList<Integer> range;


    public BoardSolver(int size){
        this.size = size;
        this.size_squared = size*size;
        this.range = IntStream.range(1, this.size_squared + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
        this.answer = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < this.size_squared; i++ ){
            ArrayList<Integer> bufferInt = new ArrayList<Integer>(this.size_squared);
            for(int j = 0; j < this.size_squared; j++){
                bufferInt.add(0);
            }
            this.answer.add(bufferInt);
        }
        
        this.setDiagonalBox();
        this.generate();

    }
    public ArrayList<ArrayList<Integer>> getAnswer(){
        return this.answer;
    }

    public void setDiagonalBox(){
        for(int i = 0; i<this.size; i++){
            Collections.shuffle(this.range);
            int offsetLower = i*this.size;
            int offsetUpper = (i+1)*this.size;
            int row = offsetLower;
            int col = offsetLower;
            for(int num: this.range){
                this.answer.get(row).set(col, num);
                col += 1;
                if(col == offsetUpper){
                    col = offsetLower;
                    row +=1;
                }
            }
        }
    }
    public boolean generate(){
        //empty index here
        for(int row = 0; row< this.size_squared; row++){
            for(int col = 0; col < this.size_squared; col++){
                if( this.answer.get(row).get(col) ==0){
                    for(int num: this.range){
                        if(checkValidity(row, col, num)){
                            this.answer.get(row).set(col,num);
                            if(generate()){
                                return true;
                            }
                            else{
                                this.answer.get(row).set(col, 0);
                            }

                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkValidity(int row, int col, int val){
        return (!this.existsinRow(row,val) && !this.existsinCol(col,val)  && !this.existsinBox(row, col, val));
        
    }

    public boolean existsinRow(int row, int val){
        for(int col = 0; col < this.size_squared; col++){
            if(this.answer.get(row).get(col) == val){
                return true;
            }
        }
        return false;
    }
    
    public boolean existsinCol(int col, int val) {
        for(int row = 0; row < this.size_squared; row++){
            if(this.answer.get(row).get(col) == val){
                return true;
            }
        }
        return false;
    }
    
    public boolean existsinBox(int row, int col, int val) {
        int rowStart = row - row % this.size;
        int colStart = col - col % this.size;

        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                if (this.answer.get(rowStart + i).get(colStart + j) == val){
                    return true;
                }
            }
        }

        return false;
    }
    
    
}
