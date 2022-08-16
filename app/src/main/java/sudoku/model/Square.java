package sudoku.model;
import sudoku.controller.*;

public class Square {

    protected int value = 0;
    public int row;
    public int col;
    private CellInterface cellInterface;
    public boolean editable = true;

    public Square(int row, int col, int value){
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int val){
        this.value = val;
    }

    public boolean equals(Square otherSquare){
        return this.getValue() == otherSquare.getValue();
    }
    
    public void addCellInterface(CellInterface sqInterface){
        this.cellInterface = sqInterface;
    }
    public CellInterface getCellInterface(){
        return this.cellInterface;
    }
    public void setEditable(boolean editable){
        this.editable = false;
    }
    public boolean getEditable(){
        return this.editable;
    }
    public int getColID(){
        return this.col;
    }
    
    public int getRowID() {
        return this.row;
    }
}