package sudoku.view;

import sudoku.controller.*;
import sudoku.model.*;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;


public class SudokuTable implements SudokuUserInterface {
    private int frameSize;
    protected JFrame mainFrame;
    private JButton undo;
    private JButton hint;
    private JButton clear;
    private PuzzlePanel puzzlePanel;
    private Board newBoard;
    private MainMenu mainMenu;
    //private ArrayList<ArrayList<Square>> miniPuzzle;


    public SudokuTable( Board newBoard){
        this.newBoard = newBoard;
        this.frameSize = 500;
        int dim = newBoard.getSize();
    
        //frame stuff
        this.mainFrame = new JFrame("Sudoku");
        this.mainFrame.setPreferredSize(new Dimension(this.frameSize, this.frameSize + 100));
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setResizable(false);

        // the container representing our puzzle table
        JPanel puzzleTable = new JPanel();
        puzzleTable.setLayout(new BoxLayout(puzzleTable, BoxLayout.Y_AXIS));

        //grid stuff
        this.puzzlePanel = new PuzzlePanel(dim, this.frameSize);
        
        ArrayList<ArrayList<Square>> miniPuzzle = newBoard.getBoxSection();
        int counter = 0;
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                ArrayList<Square> board1 = miniPuzzle.get(counter);
                this.puzzlePanel.add(board1, row, col);
                counter +=1;
            }
        }
        puzzleTable.add(puzzlePanel);

        //Clearing board class
        ClearBoard clearBoard = new ClearBoard();
        UndoMove undoMove = new UndoMove();

        //button stuff
        JPanel controlPanel = new JPanel();
        this.undo = new JButton("Undo");
        this.undo.addActionListener(undoMove);
        this.undo.addActionListener(undoMove);
        this.hint = new JButton("Hint");
        this.clear = new JButton("Clear Board");
        this.clear.addActionListener(clearBoard);
        controlPanel.add(this.undo);
        controlPanel.add(this.hint);
        controlPanel.add(this.clear);

        puzzleTable.add(controlPanel);

        //put everything to frame
        this.mainFrame.add(puzzleTable);
        this.mainFrame.pack();
        this.mainFrame.setVisible(false);;
    }


    @Override
    public void eraseValue(int row, int col){
        this.newBoard.setValue(row, col, 0);
        this.newBoard.getSquare(row,col).getCellInterface().eraseValue();
    }

    @Override
    public void changeRedSquare(HashSet<Square> squareList){
        for(Square temp: squareList){
            System.out.format("Violation coordinate (%d,%d)\n", temp.getRowID(), temp.getColID());
            System.out.format("Value %d\n", temp.getValue());

        }
        boolean violation;
        for(Square sq: this.newBoard.getAllSquares()){
            violation = squareList.contains(sq);
            
            if (violation){
                sq.getCellInterface().setViolation(violation);
                sq.getCellInterface().changeToInvalid();
            }
            else{
                    sq.getCellInterface().setViolation(violation);
                    sq.getCellInterface().setDefault();       
            }  
        }
    }
    
    @Override
    public void colorRelevance(int row, int col){
        
        ArrayList<Square> sectionRelevance = new ArrayList<Square> ();
        sectionRelevance.addAll(this.newBoard.getBox(row, col));
        sectionRelevance.addAll(this.newBoard.getCol(col));
        sectionRelevance.addAll(this.newBoard.getRow(row));
        
        HashSet<Square> relSet = new HashSet<Square>(sectionRelevance);
        relSet.remove(this.newBoard.getSquare(row, col));

        for (Square sq : this.newBoard.getAllSquares()) {
            if (relSet.contains(sq)) {
                sq.getCellInterface().changeToRelevance();
            }
            else{
                sq.getCellInterface().setDefault();
            }
        }
        for(Square invalid: this.newBoard.getInvalidSquares()){
            invalid.getCellInterface().changeToInvalid();
        }
    }
    
    @Override
    public void endGame(){
        int response = JOptionPane.showConfirmDialog(this.mainFrame, "End game now?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            System.out.println("return was clicked");
            this.mainFrame.setVisible(false);
            this.mainMenu.mainFrame.setVisible(true);
            for (Square square: newBoard.getAllSquares()) {
                Cell cell = new Cell(square, 1);
                cell.eraseValue();
                newBoard.getSquare(square.row, square.col).getCellInterface().eraseValue();
               // score += 1
            }
        }

    }

    public void addMainMenu(MainMenu menu){
        this.mainMenu = menu;
    }
    
    public class ClearBoard implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == clear) {
                System.out.println("clear was clicked");
                for (Square square : newBoard.getAllSquares()) {
                    if (square.editable == true) {
                        square.getCellInterface().eraseValue();

                    }
                }
            }
        }
    }

    public class UndoMove implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == undo) {
                for (Square square : newBoard.getAllSquares()) {
                    if (square.editable == true && square.getCellInterface().getLastChange()) {  //TODO: Should be doing serilizable here. but fine...
                        int prevStep = square.getCellInterface().getPrevStep();
                        square.getCellInterface().setVal(prevStep);
                    }
                }
            }
        }
    }


}