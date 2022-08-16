package sudoku.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class Board{

    // the board can generate the puzzle, some text field
    //private String filename;
    protected int size;
    protected int size_squared;
    protected ArrayList<ArrayList<Square>> puzzle;
    protected ArrayList<ArrayList<Integer>> answer;
    protected  BoxSection boxSection ;
    protected RowSection rowSection ;
    protected ColumnSection colSection;



    public Board(ArrayList<ArrayList<Square>> puzzle){
        this.puzzle = new ArrayList<ArrayList<Square>>();
        this.answer = new  ArrayList<ArrayList<Integer>>();
        
        for(ArrayList<Square> sqList: puzzle){
            ArrayList<Integer> row_vec = new ArrayList<Integer>();
            ArrayList<Square> sq_vec = new ArrayList<Square>();
            for(Square sq: sqList){
                int val = sq.getValue();
                row_vec.add(val);
                sq_vec.add(new Square(sq.getRowID(), sq.getColID(), val));
            }
            this.answer.add(row_vec);
            this.puzzle.add(sq_vec);
        }
        this.size_squared = puzzle.size();
        this.size = (int) Math.sqrt(size_squared);
        this.rowSection = new RowSection(this.puzzle);
        this.colSection = new ColumnSection(this.puzzle);
        this.boxSection = new BoxSection(this.puzzle);
        
    }
    
    // constructor with difficulty
    public Board(int size, int numRandValues){
        this.size = size;
        this.size_squared = size*size;
        this.puzzle = new ArrayList<ArrayList<Square>> ();
        for (int i = 0; i <this.size_squared; i++){
            ArrayList<Square> bufferSquared = new ArrayList<Square> (this.size_squared);
            for (int j = 0; j < this.size_squared; j++){
                Square sq = new Square(i, j, 0);
                bufferSquared.add(sq);
                
            }
            //System.out.println(bufferSquared.get(0).getValue());
            this.puzzle.add(bufferSquared);
            //System.out.println(this.puzzle.get(i).get(0).getValue());
        }
        
        this.rowSection = new RowSection(this.puzzle);
        this.colSection = new ColumnSection(this.puzzle);
        this.boxSection = new BoxSection(this.puzzle);
        this.answer = new BoardSolver(size).getAnswer();

        this.generateBoard(numRandValues);
        //System.out.println(this.puzzle.get(0).get(0).getValue());
        
        //System.out.println(this.boxSection.getTransform().get(0).get(0).getValue());
        

    }
    public int getSize(){
        return size;
    }
    private void generateBoard(int numRandValues){
        
        Random rand = new Random();
        for (int i = 0; i < numRandValues; i++) {
            int rand_row = rand.nextInt(this.size_squared);
            int rand_col = rand.nextInt(this.size_squared);
            if(this.getSquare(rand_row,rand_col).getValue() == 0){
                this.setValue(rand_row, rand_col, this.answer.get(rand_row).get(rand_col));
                this.getSquare(rand_row,rand_col).setEditable(false);
            }
            else{
                i-=1;
            }
        }        
    }
    public ArrayList<ArrayList<Square>> getPuzzle(){
        return this.puzzle;
    }
    public ArrayList<ArrayList<Square>> getBoxSection(){
        return this.boxSection.getTransform();
    }
    public Square getSquare(int row, int col){
        return this.puzzle.get(row).get(col);
    }
    public ArrayList<Square> getRow(int row){
        return this.rowSection.getByIndex(row);
    }
    public ArrayList<Square> getCol(int col) {
        return this.colSection.getByIndex(col);
    }
    public ArrayList<Square> getBox(int row, int col){
        return this.boxSection.getByIndex(row, col);
    }

    public void setValue(int row, int col, int val) {
        System.out.format("setting %d at (%d, %d)\n", val, row, col);
        this.getSquare(row,col).setValue(val);
    }
    

    
    public boolean isComplete() {
        System.out.println("Checking if board is all filled out and correctly");
        for (ArrayList<Square> row: this.puzzle){
            for(Square sq: row){
                if(sq.getValue() == 0){
                    return false;
                }
            }
        }
        return true;

    }
    public boolean isInRange(int val) {
        System.out.println("Checking in range");
        if (val >= 0 && val <= this.size_squared) {
            return true;
        }
        return false; 
    } 
    
    public HashSet<Square> getInvalidSquares(){
        HashSet<Square> invalCol = colSection.getInvalids();
        HashSet<Square> invalRow = rowSection.getInvalids();
        HashSet<Square> invalBox = boxSection.getInvalids();
        HashSet<Square> invalAll = new HashSet<Square>();
        invalAll.addAll(invalCol);
        invalAll.addAll(invalRow);
        invalAll.addAll(invalBox);
        return (invalAll);
    }

    public String toString(){
        int dim = this.size;
        int counter;
        String toPrint = "";

        for (int i = 0; i < this.size_squared; i ++) {
            counter = 0;
            for (int j = 0; j < this.size_squared; j ++) {
                toPrint += String.valueOf(this.getSquare(i,j).getValue());
                toPrint += " ";
                counter +=2;
                if (j % 3 == 2) {
                    toPrint += "|";
                    counter += 1;
                }
            }
            toPrint += "\n";
            if (i % dim == 2) {
                toPrint += "-".repeat(counter) + "\n";
            }
    }
    return toPrint;
}


    public ArrayList<Square> getAllSquares(){
        ArrayList<Square> toReturnAllSquare = new ArrayList<Square> (this.size_squared);
        for(ArrayList<Square> sqList: this.puzzle){
            for(Square sq: sqList){
                toReturnAllSquare.add(sq);
            }
        }
        return toReturnAllSquare;
    }
    public Board clone(){
        Board boardCopy = new Board(this.puzzle);
        return boardCopy;
    }
}
