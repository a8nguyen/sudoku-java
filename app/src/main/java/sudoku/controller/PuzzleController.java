package sudoku.controller;
import java.util.ArrayList;
import java.util.HashSet;
import sudoku.model.*;
public class PuzzleController {
    protected Board board; //a board to check
    protected SudokuUserInterface boardUI;
    protected String difficulty;

    public PuzzleController(String difficulty){
        this.difficulty = difficulty;
        if (this.difficulty == "EASY") {
            this.board = new Board(2, 3);
        }
        if (this.difficulty == "MEDIUM") {
            this.board = new Board(3, 30);
        }
        if (this.difficulty == "HARD") {
            this.board = new Board(4, 130);
        }
    }


    public void userEditting(int row, int col){
        CellInterface cell = board.getSquare(row,col).getCellInterface();
        if (cell.getEditable()){
            this.boardUI.colorRelevance(row, col);
        }
    }

    public boolean userEnteredValue(int row, int col, int val){
        
        //use case 1:if value is out of range
        System.out.println(this.board);
        CellInterface cell = board.getSquare(row,col).getCellInterface();
        if (!this.board.isInRange(val)){
            boardUI.eraseValue(row, col);
            return false;   
        }
        this.board.setValue(row,col,val);
        
        HashSet<Square> invalidSquares = this.board.getInvalidSquares();
        if(invalidSquares.size() == 0){
            boardUI.changeRedSquare(invalidSquares);
        }
        else{
            if(this.board.isComplete()){
                this.gameOver();
            }
        }
        return true; 
    }

    public void gameOver(){
        boardUI.endGame();
        
    }

    public void startGame(){

        int temp = 0;
        for (ArrayList<Square> arSquare: this.board.getPuzzle()){
            for(Square square: arSquare){
                if (square.getCellInterface() == null){
                    temp += 1;
                }

                else{
                square.getCellInterface().addController(this);

                }
            }
        }
        System.out.println(temp);

    }
    public Board getBoard(){
        return this.board;
    }
    public void addUserInterface(SudokuUserInterface ui){
        this.boardUI = ui;
    }

    
}
